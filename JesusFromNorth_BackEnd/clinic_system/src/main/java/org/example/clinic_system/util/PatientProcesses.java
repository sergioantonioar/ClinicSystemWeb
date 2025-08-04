package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Patient;

import java.util.List;

public class PatientProcesses {

    public static List<PatientDTO> CreatePatientResponseDTO(List<Patient>listPatient) {
        return listPatient.stream()
                .map(PatientProcesses::createPatientDTO)
                .toList();
    }

    public static Patient CreatePatient(PatientResponseDTO patientResponseDTO, Admin admin){
        return Patient.builder()
                .age(patientResponseDTO.getAge())
                .dni(patientResponseDTO.getDni())
                .birthdate(patientResponseDTO.getBirthdate())
                .first_name(patientResponseDTO.getFirst_name())
                .last_name(patientResponseDTO.getLast_name())
                .email(patientResponseDTO.getEmail())
                .address(patientResponseDTO.getAddress())
                .gender(patientResponseDTO.getGender())
                .antecedent(patientResponseDTO.getAntecedent())
                .phone(patientResponseDTO.getPhone())
                .admin(admin)
                .landline_phone(patientResponseDTO.getLandline_phone())
                .build();
    }

    public static PatientResponseDTO createPatientResponseDTO(Patient patient){
        return PatientResponseDTO.builder()
                .age(patient.getAge())
                .dni(patient.getDni())
                .birthdate(patient.getBirthdate())
                .first_name(patient.getFirst_name())
                .last_name(patient.getLast_name())
                .email(patient.getEmail())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .antecedent(patient.getAntecedent())
                .phone(patient.getPhone())
                .landline_phone(patient.getLandline_phone())
                .build();
    }

    public static PatientDTO createPatientDTO(Patient patient){
        return PatientDTO.builder()
                .id_patient(patient.getId_patient())
                .age(patient.getAge())
                .dni(patient.getDni())
                .birthdate(patient.getBirthdate())
                .first_name(patient.getFirst_name())
                .last_name(patient.getLast_name())
                .email(patient.getEmail())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .antecedent(patient.getAntecedent())
                .phone(patient.getPhone())
                .landline_phone(patient.getLandline_phone())
                .build();
    }


    public static Patient updatePatient(Patient patient, PatientResponseDTO patientResponseDTO) {
        PersonProcesses.UpdatePerson(patient,patientResponseDTO);
        if (patientResponseDTO.getBirthdate() != null) {
            patient.setBirthdate(patientResponseDTO.getBirthdate());
        }
        if (patientResponseDTO.getGender() != null) {
            patient.setGender(patientResponseDTO.getGender());
        }
        if (patientResponseDTO.getAge() != null) {
            patient.setAge(patientResponseDTO.getAge());
        }
        if (patientResponseDTO.getAntecedent() != null) {
            patient.setAntecedent(patientResponseDTO.getAntecedent());
        }
        return patient;
    }

}
