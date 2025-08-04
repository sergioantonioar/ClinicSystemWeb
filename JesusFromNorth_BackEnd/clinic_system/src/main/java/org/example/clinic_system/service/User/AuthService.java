package org.example.clinic_system.service.User;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.AdminDTO;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.AuthResponse;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.jwt.JwtUtils;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.util.AdminProcesses;
import org.example.clinic_system.util.DoctorProcesses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AdminService adminService;
    private final DoctorService doctorService;

    public AuthResponse<?> authenticate(String username, String password) throws NotFoundException {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateToken(authentication);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User no encontrado"));

        UUID id_user = user.getId_user();

        if (user.getRole() == Rol.ADMIN) {
            Admin admin = adminService.findByUser(id_user);
            return AuthResponse.<AdminDTO>builder()
                    .token(jwt)
                    .role(user.getRole())
                    .data(AdminProcesses.CreateAdminDTO(admin))
                    .build();
        }

        //Si el dotor esta eliminado no puede iniciar sesion
        if (user.getRole() == Rol.DOCTOR) {
            Doctor doctor = doctorService.getDoctorByIdUser(id_user);
            return AuthResponse.<DoctorDTO>builder()
                    .token(jwt)
                    .role(user.getRole())
                    .data(DoctorProcesses.CreateDoctorDTO(doctor))
                    .build();
        }

        throw new NotFoundException("Rol no soportado: " + user.getRole());
    }


    public User registerUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("El usename ya esta registrado");
        }
        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        return userRepository.save(newUser);
    }

}
