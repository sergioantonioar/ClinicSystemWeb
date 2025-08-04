package org.example.clinic_system.service.ServiceAux;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.service.ServiceSpecialty.ServiceService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.SpecialtyProcesses;
import org.springframework.stereotype.Service;

import java.util.UUID;

//Preferible que se use en el controlador de SpecialtyController
@Service
@RequiredArgsConstructor
public class SpecialtyWithServiceImp implements SpecialtyWithService{

    private final SpecialtyService specialtyService;
    private final ServiceService serviceService;

    @Override
    public SpecialtyWithServicesDTO getSpecialtyWithServiceDTOById(UUID id_specialty) throws NotFoundException {
        return SpecialtyProcesses.TransformSpecialtyWithServiceDTO(
                specialtyService.getSpecialtyById(id_specialty),
                serviceService.getAllServicesBySpecialty(id_specialty)
        );
    }

    @Override
    public SpecialtyWithServicesDTO getSpecialtyByNameSpecialty(String nameSpecialty) throws NotFoundException {
        // Obtener la especialidad por nombre utilizando specialtyService
        Specialty specialty = specialtyService.getSpecialtyByName(nameSpecialty);
        return  SpecialtyProcesses.TransformSpecialtyWithServiceDTO(
                specialty,
                serviceService.getAllServicesBySpecialty(specialty.getId_specialty())
        );
    }

}
