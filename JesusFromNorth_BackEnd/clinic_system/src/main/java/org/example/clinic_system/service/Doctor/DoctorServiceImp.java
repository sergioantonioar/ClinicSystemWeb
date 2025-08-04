package org.example.clinic_system.service.Doctor;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseWithIDSpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;
import org.example.clinic_system.repository.DoctorRepository;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.DoctorProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyService specialtyService;
    private final AdminService adminService;

    @Value("${page-size}")
    private int size;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Este crear al doctor con su usuario, pero necesita que le ingrese el username y el password para crear: retorna (entidad, uuid)
    @Override
    public Tuple<DoctorResponseDTO, UUID> SaveDoctorWithUsername(RegisterDoctorDTO registerDoctorDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {

        if(userRepository.existsByUsername(registerDoctorDTO.getUsername())){
            throw new IllegalArgumentException("El usename ya esta registrado");
        }

        Specialty specialty = specialtyService.getSpecialtyById(id_specialist);

        Admin admin = adminService.findById(id_admin);

        User user = User.builder()
                .username(registerDoctorDTO.getUsername())
                .password(passwordEncoder.encode(registerDoctorDTO.getPassword()))
                .role(Rol.DOCTOR)
                .build();

        user = userRepository.save(user);

        Doctor doctorResponseDTO = doctorRepository.save(DoctorProcesses.CreateDoctorWithUsername(registerDoctorDTO,specialty,admin,user));

        return Tuple.
                <DoctorResponseDTO,UUID>builder()
                .first(DoctorProcesses.CreateDoctorEntity(doctorResponseDTO))
                .second(doctorResponseDTO.getId_doctor())
                .build();

    }

    //Este crear al doctor con su usuario, pero necesitas solo el password para crear, ya que su username es su dni: retorna (entidad, uuid)
    @Override
    public Tuple<DoctorResponseDTO, UUID> SaveDoctorWithoutUsername(RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {
        if(userRepository.existsByUsername(registerDoctorNoUsernameDTO.getDni())){
            throw new IllegalArgumentException("El usename ya esta registrado");
        }
        Specialty specialty = specialtyService.getSpecialtyById(id_specialist);
        Admin admin = adminService.findById(id_admin);
        User user = User.builder()
                .username(registerDoctorNoUsernameDTO.getDni())
                .password(passwordEncoder.encode(registerDoctorNoUsernameDTO.getPassword()))
                .role(Rol.DOCTOR)
                .build();
        user = userRepository.save(user);
        Doctor doctorResponseDTO = doctorRepository.save(DoctorProcesses.CreateDoctorNoUsername(registerDoctorNoUsernameDTO,specialty,admin,user));
        return Tuple.
                <DoctorResponseDTO,UUID>builder()
                .first(DoctorProcesses.CreateDoctorEntity(doctorResponseDTO))
                .second(doctorResponseDTO.getId_doctor())
                .build();
    }

    @Override
    public DoctorDTO getDoctorDTOById(UUID id_doctor) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        return DoctorProcesses.CreateDoctorDTO(doc);
    }

    //Me trae todos los doctores
    @Override
    public List<DoctorDTO> getAllDoctors(int page) {
        return DoctorProcesses
                .CreateDoctorResponseDTO(
                        doctorRepository.findAll(
                                PageRequest.of(page,size)
                        ).getContent()
                );
    }

    @Override
    public List<DoctorDTO> getAllDoctorsBySpecialist(UUID id_specialist, int page) {
        return DoctorProcesses.CreateDoctorResponseDTO(
                doctorRepository.findAllBySpecialty(
                        id_specialist,
                        PageRequest.of(page, size)
                ).getContent()
        );
    }

    //Este es para actualizar el doctor incluso su tipo de especialidad
    @Override
    public void updateDoctor(UUID id_doctor, DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        Specialty specialty = specialtyService.getSpecialtyById(doctorResponseDTO.getId_specialty());
        doctorRepository.save(DoctorProcesses.UpdateDoctor(doc,doctorResponseDTO,specialty));
    }

    @Override
    public void deleteDoctor(UUID id_doctor) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        doc.setIs_deleted(true);
        doctorRepository.save(doc);
    }

    @Override
    public Doctor getDoctorById(UUID id_doctor) throws NotFoundException{
        return doctorRepository.findById(id_doctor)
                .orElseThrow(() -> new NotFoundException("No se encontro al doctor"));
    }

    //para obtener por cmp
    @Override
    public DoctorDTO getDoctorByCmp(String cmp) throws NotFoundException {
        Doctor doctor = doctorRepository.findByCmp(cmp)
                .orElseThrow(() -> new NotFoundException("No se encontró un doctor con el CMP: " + cmp));
        return DoctorProcesses.CreateDoctorDTO(doctor);

    }

    @Override
    public DoctorDTO getDoctorByDni(String dni) throws NotFoundException {
        Doctor doctor = doctorRepository.findByDni(dni)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        return DoctorProcesses.CreateDoctorDTO(doctor);
    }

    @Override
    public Doctor getDoctorByIdUser(UUID id_user) throws NotFoundException {
        return doctorRepository.findByUser(id_user)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
    }

}
