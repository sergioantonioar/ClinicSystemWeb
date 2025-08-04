package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.model.ServiceSpecialty;
import org.example.clinic_system.model.Specialty;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceProcesses {
    public static ServiceSpecialty getService(ServiceDTO serviceDTO, Specialty specialty) {
        return ServiceSpecialty.builder()
                .service_name(serviceDTO.getName_Service())
                .price(serviceDTO.getPrice())
                .specialty(specialty)
                .build();
    }

    public static ServiceResponseDTO getSpecialty(ServiceSpecialty serviceSpecialty) {
        return ServiceResponseDTO.builder()
                .name_service(serviceSpecialty.getService_name())
                .price(serviceSpecialty.getPrice())
                .build();
    }

    public static ServiceDTO getDTO(ServiceSpecialty serviceSpecialty) {
        return ServiceDTO.builder()
                .id_service(serviceSpecialty.getId_service())
                .name_Service(serviceSpecialty.getService_name())
                .price(serviceSpecialty.getPrice())
                .build();
    }

    public static List<ServiceResponseDTO> TransformListServiceResponseDTO(List<ServiceSpecialty> bySpecialtyId) {
        return bySpecialtyId
                .stream()
                .map(ServiceProcesses::getSpecialty)
                .toList();
    }

    public static List<ServiceDTO> TransformListServiceDTO(List<ServiceSpecialty> bySpecialtyId) {
        return bySpecialtyId.stream()
                .map(ServiceProcesses::getDTO)
                .toList();
    }
}
