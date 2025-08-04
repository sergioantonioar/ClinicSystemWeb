package org.example.clinic_system.service.Specialty;

import lombok.AllArgsConstructor;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.repository.SpecialtyRepository;
import org.example.clinic_system.util.SpecialtyProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SpecialtyServiceImp implements SpecialtyService{

    private SpecialtyRepository specialtyRepository;

    //Guarda una especialidad
    @Override
    public Tuple<SpecialtyResponseDTO,UUID> saveSpecialty(SpecialtyResponseDTO specialtyDTO) {
        Specialty specialty = specialtyRepository.save(SpecialtyProcesses.CreateSpecialty(specialtyDTO));
        return Tuple.
                <SpecialtyResponseDTO,UUID>builder()
                .first(SpecialtyProcesses.CreateSpecialtyDTO(specialty))
                .second(specialty.getId_specialty())
                .build();
    }

    //Me borra una especiliadad: El repositorio ya filtra los que no esten eliminados
    @Override
    public void deleteSpecialty(UUID id_specialty) throws NotFoundException {
        Specialty specialty = specialtyRepository.findById(id_specialty)
                .orElseThrow( () -> new NotFoundException("No se encontro la especialidad con el id: " + id_specialty));
        specialty.setIs_deleted(true);
        specialtyRepository.save(specialty);
    }

    //Me devuelve la lista de especualdades
    @Override
    public List<SpecialtyDTO> getAllSpecialties() {
        return SpecialtyProcesses
                .TransformListSpecialtyDTO(specialtyRepository.findAll());
    }

    //Este lo usa otro servicio, No va ser Usado en el controlador se especialidad
    @Override
    public Specialty getSpecialtyById(UUID id_specialty) throws NotFoundException {
        return specialtyRepository.findById(id_specialty)
                .orElseThrow( () -> new NotFoundException("No se encontro la especialidad con el id: " + id_specialty));
    }

    //Esto lo usa otro servicio, No va a ser usando en el controlador
    @Override
    public Specialty getSpecialtyByName(String name) throws NotFoundException {
        return specialtyRepository.findByName(name)
                .orElseThrow( () -> new NotFoundException("No se encontro la especialidad con el nombre: " + name));
    }

}
