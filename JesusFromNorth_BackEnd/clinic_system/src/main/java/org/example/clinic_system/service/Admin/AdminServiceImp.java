package org.example.clinic_system.service.Admin;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;
import org.example.clinic_system.repository.AdminRepository;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.service.User.AuthService;
import org.example.clinic_system.util.AdminProcesses;
import org.example.clinic_system.util.UserProcesses;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void save(RegisterAdminDTO registerAdminDTO) {
        // Crear y guardar User directamente
        if(userRepository.existsByUsername(registerAdminDTO.getUsername())){
            throw new IllegalArgumentException("El usename ya esta registrado");
        }

        User user = User.builder()
                .username(registerAdminDTO.getUsername())
                .password(passwordEncoder.encode(registerAdminDTO.getPassword()))
                .role(Rol.ADMIN)
                .build();

        user = userRepository.save(user); // sin usar AuthService

        // Crear y guardar Admin
        Admin newAdmin = AdminProcesses.CreateAdmin(registerAdminDTO, user);
        adminRepository.save(newAdmin);
    }

    @Override
    public Admin findByDni(String dni) throws NotFoundException {
        return adminRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

    @Override
    public Admin findById(UUID id_admin) throws NotFoundException {
        return adminRepository.findById(id_admin)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

    @Override
    public Admin findByUser(UUID id_user) throws NotFoundException {
        return adminRepository.findByUser(id_user)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

}
