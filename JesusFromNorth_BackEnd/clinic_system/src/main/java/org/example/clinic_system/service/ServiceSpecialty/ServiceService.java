package org.example.clinic_system.service.ServiceSpecialty;

import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface ServiceService {
    Tuple<ServiceResponseDTO,UUID> saveService(ServiceDTO service, UUID id_specialty) throws NotFoundException;
    ServiceDTO getServiceById(UUID id_service) throws NotFoundException;
    void updateService(ServiceDTO service, UUID id_specialty) throws NotFoundException;
    void deleteService(UUID id_service) throws NotFoundException;
    List<ServiceDTO> getAllServicesBySpecialty(UUID id_specialty);

}
