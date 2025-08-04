package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.service.ServiceAux.SpecialtyWithService;

import java.util.List;


public class SpecialtyProcesses {
    public static Specialty CreateSpecialty(SpecialtyResponseDTO specialty) {
        return Specialty.builder()
                .specialty_name(specialty.getName_specialty())
                .build();
    }

    public static SpecialtyResponseDTO CreateSpecialtyDTO(Specialty specialty) {
        return SpecialtyResponseDTO.builder()
                .name_specialty(specialty.getSpecialty_name())
                .build();
    }

    public static SpecialtyDTO TransformSpecialtyDTO(Specialty specialty) {
        return SpecialtyDTO.builder()
                .id_specialty(specialty.getId_specialty())
                .specialty_name(specialty.getSpecialty_name())
                .build();
    }

    public static List<SpecialtyDTO> TransformListSpecialtyDTO(List<Specialty> specialty) {
        return specialty.stream()
                .map(SpecialtyProcesses::TransformSpecialtyDTO)
                .toList();
    }

    public static SpecialtyWithServicesDTO TransformSpecialtyWithServiceDTO(Specialty specialty, List<ServiceDTO>serviceResponseDTOS) {
        return SpecialtyWithServicesDTO.builder()
                .id_specialty(specialty.getId_specialty())
                .name_specialty(specialty.getSpecialty_name())
                .ListServicesDTO(serviceResponseDTOS)
                .build();
    }
}
