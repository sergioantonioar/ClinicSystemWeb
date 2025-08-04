package org.example.clinic_system.service.ServiceSpecialty;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.ServiceSpecialty;
import org.springframework.stereotype.Service;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.repository.ServiceRepository;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.ServiceProcesses;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {

    private final ServiceRepository serviceRepository;

    private final SpecialtyService specialtyService;

    //ME GUARDA UN SERVICIO
    @Override
    public Tuple<ServiceResponseDTO,UUID> saveService(ServiceDTO serviceDTO, UUID id_specialty) throws NotFoundException {
        Specialty specialty = specialtyService.getSpecialtyById(id_specialty);
        ServiceSpecialty serviceSpecialty = serviceRepository.save(ServiceProcesses.getService(serviceDTO,specialty));
        return Tuple.
                <ServiceResponseDTO,UUID> builder()
                .first(ServiceProcesses.getSpecialty(serviceSpecialty))
                .second(specialty.getId_specialty())
                .build();
    }

    @Override
    public ServiceDTO getServiceById(UUID id_service) throws NotFoundException {
        return ServiceProcesses.getDTO(
                serviceRepository.findById(id_service)
                        .orElseThrow(()-> new NotFoundException("No se encontro el Servicio"))
        );
    }

    @Override
    public void updateService(ServiceDTO service, UUID id_specialty) {

    }

    @Override
    public void deleteService(UUID id_service) {

    }

    //Me devuelve todos los servicios de una especialidad,LO UTILIZA OTROSERVICIO,NO SE VA USAR EN EL CONTROLADOR
    @Override
    public List<ServiceDTO> getAllServicesBySpecialty(UUID id_specialty) {
        return ServiceProcesses
                .TransformListServiceDTO(serviceRepository.findBySpecialtyId(id_specialty));
    }

}
