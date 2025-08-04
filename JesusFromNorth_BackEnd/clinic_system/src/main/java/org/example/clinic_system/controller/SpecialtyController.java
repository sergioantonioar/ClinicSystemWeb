package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.ServiceAux.SpecialtyWithService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("specialty")
@RequiredArgsConstructor
@Tag(name = "Specialty", description = "Endpoints para gestión de especialidades")
public class SpecialtyController {

  private final SpecialtyService specialtyService;
  private final SpecialtyWithService specialtyWithService;

  @Operation(summary = "Guardar especialidad", description = "Guarda una nueva especialidad en el sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Especialidad guardada exitosamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @PostMapping("/")
  public ResponseEntity<?> saveSpecialty(@RequestBody SpecialtyResponseDTO specialtyDTO) {
    Tuple<SpecialtyResponseDTO, UUID> result = specialtyService.saveSpecialty(specialtyDTO);
    SuccessMessage<SpecialtyResponseDTO> successMessage = SuccessMessage.<SpecialtyResponseDTO>builder()
        .status(HttpStatus.CREATED.value())
        .message("La especialidad fue guardada exitosamente")
        .data(result.getFirst())
        .build();
    URI location = UriGeneric.CreateUri("/{id}", result.getSecond());
    return ResponseEntity.created(location).body(successMessage);
  }

  @Operation(summary = "Eliminar especialidad por ID", description = "Elimina una especialidad existente usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Especialidad eliminada exitosamente", content = @Content),
      @ApiResponse(responseCode = "404", description = "Especialidad no encontrada", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSpecialty(@PathVariable("id") UUID id_specialty) throws NotFoundException {
    specialtyService.deleteSpecialty(id_specialty);
    SuccessMessage<Void> successMessage = SuccessMessage.<Void>builder()
        .status(HttpStatus.NO_CONTENT.value())
        .message("La especialidad fue eliminada exitosamente")
        .build();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }

  @Operation(summary = "Listar todas las especialidades", description = "Devuelve todas las especialidades registradas en el sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/list")
  public ResponseEntity<?> getAllSpecialties() {
    List<SpecialtyDTO> specialties = specialtyService.getAllSpecialties();
    SuccessMessage<List<SpecialtyDTO>> successMessage = SuccessMessage.<List<SpecialtyDTO>>builder()
        .status(HttpStatus.OK.value())
        .message("Lista de especialidades obtenida correctamente")
        .data(specialties)
        .build();

    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Obtener especialidad con servicios por ID", description = "Devuelve una especialidad junto a sus servicios asociados mediante el ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Especialidad obtenida exitosamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Especialidad no encontrada", content = @Content)
  })
  @GetMapping("/{id}/services")
  public ResponseEntity<?> getSpecialtyWithServices(@PathVariable("id") UUID id_specialty) throws NotFoundException {
    SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyWithServiceDTOById(id_specialty);
    SuccessMessage<SpecialtyWithServicesDTO> successMessage = SuccessMessage.<SpecialtyWithServicesDTO>builder()
        .status(HttpStatus.OK.value())
        .message("La especialidad con los servicios fue obtenida correctamente")
        .data(specialtyWithServices)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Buscar especialidad por nombre", description = "Busca una especialidad y sus servicios mediante el nombre.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Especialidad encontrada exitosamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Especialidad no encontrada", content = @Content)
  })
  @GetMapping("/name/{name}")
  public ResponseEntity<?> getSpecialtyByName(@PathVariable("name") String nameSpecialty) throws NotFoundException {
    SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyByNameSpecialty(nameSpecialty);
    SuccessMessage<SpecialtyWithServicesDTO> successMessage = SuccessMessage.<SpecialtyWithServicesDTO>builder()
        .status(HttpStatus.OK.value())
        .message("La especialidad con servicios fue obtenida exitosamente por nombre")
        .data(specialtyWithServices)
        .build();
    return ResponseEntity.ok(successMessage);
  }
}
