package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseWithIDSpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;

import java.util.List;
import java.util.stream.Collectors;


public class DoctorProcesses {

    public static Doctor CreateDoctorWithUsername(RegisterDoctorDTO doctorDTO, Specialty specialty, Admin admin,User user) {
        return Doctor.builder()
                .first_name(doctorDTO.getFirst_name())
                .last_name(doctorDTO.getLast_name())
                .email(doctorDTO.getEmail())
                .address(doctorDTO.getAddress())
                .phone(doctorDTO.getPhone())
                .landline_phone(doctorDTO.getLandline_phone())
                .dni(doctorDTO.getDni())
                .cmp(doctorDTO.getCmp())
                .specialty(specialty)
                .user(user)
                .admin(admin)
                .build();
    }

    public static Doctor CreateDoctorNoUsername(RegisterDoctorNoUsernameDTO doctorDTO, Specialty specialty, Admin admin,User user ) {
        return Doctor.builder()
                .first_name(doctorDTO.getFirst_name())
                .last_name(doctorDTO.getLast_name())
                .email(doctorDTO.getEmail())
                .address(doctorDTO.getAddress())
                .phone(doctorDTO.getPhone())
                .landline_phone(doctorDTO.getLandline_phone())
                .dni(doctorDTO.getDni())
                .cmp(doctorDTO.getCmp())
                .specialty(specialty)
                .user(user)
                .admin(admin)
                .build();
    }

    public static Doctor UpdateDoctor(Doctor doctor, DoctorResponseWithIDSpecialtyDTO doctorResponseDTO, Specialty specialty) {
        PersonProcesses.UpdatePerson(doctor, doctorResponseDTO);

        if(specialty!=null){
            doctor.setSpecialty(specialty);
        }

        if (doctorResponseDTO.getCmp() != null) {
            doctor.setCmp(doctorResponseDTO.getCmp());
        }
        return doctor;
    }

    public static DoctorResponseDTO CreateDoctorEntity(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .first_name(doctor.getFirst_name())
                .last_name(doctor.getLast_name())
                .email(doctor.getEmail())
                .address(doctor.getAddress())
                .phone(doctor.getPhone())
                .landline_phone(doctor.getLandline_phone())
                .dni(doctor.getDni())
                .cmp(doctor.getCmp())
                .specialty(doctor.getSpecialty())
                .build();
    }

    public static DoctorResponseWithIDSpecialtyDTO CreateDoctorEntityWithIDSpecialty(Doctor doctor) {
        return DoctorResponseWithIDSpecialtyDTO.builder()
                .first_name(doctor.getFirst_name())
                .last_name(doctor.getLast_name())
                .email(doctor.getEmail())
                .address(doctor.getAddress())
                .phone(doctor.getPhone())
                .landline_phone(doctor.getLandline_phone())
                .dni(doctor.getDni())
                .cmp(doctor.getCmp())
                .id_specialty(doctor.getSpecialty().getId_specialty())
                .build();
    }

    public static DoctorDTO CreateDoctorDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .id_doctor(doctor.getId_doctor())
                .specialty(doctor.getSpecialty())
                .cmp(doctor.getCmp())
                .first_name(doctor.getFirst_name())
                .last_name(doctor.getLast_name())
                .email(doctor.getEmail())
                .address(doctor.getAddress())
                .phone(doctor.getPhone())
                .landline_phone(doctor.getLandline_phone())
                .dni(doctor.getDni())
                .build();
    }

    public static List<DoctorDTO> CreateDoctorResponseDTO(List<Doctor>listDoctor) {
        return listDoctor.stream()
                .map(DoctorProcesses::CreateDoctorDTO)
                .toList();
    }

}
