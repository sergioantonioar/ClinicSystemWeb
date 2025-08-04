package org.example.clinic_system.service.ServiceAux;

import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.handler.NotFoundException;

import java.util.UUID;

//Preferible que se use en el controlador de SpecialtyController
public interface SpecialtyWithService {
    SpecialtyWithServicesDTO getSpecialtyWithServiceDTOById(UUID id_specialty) throws NotFoundException;
    SpecialtyWithServicesDTO getSpecialtyByNameSpecialty(String nameSpecialty) throws NotFoundException;
}
