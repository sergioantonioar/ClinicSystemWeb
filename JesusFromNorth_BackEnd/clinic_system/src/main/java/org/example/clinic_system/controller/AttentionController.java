package org.example.clinic_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.BadRequestException;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Attention.AttentionService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attention")
@RequiredArgsConstructor
public class AttentionController {

  private final AttentionService attentionService;

  @Operation(summary = "Registrar una atención médica", description = "Guarda una nueva atención médica vinculada a una cita ya existente.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Atención registrada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
  })
  @PostMapping("/appointment/{id_appointment}")
  public ResponseEntity<?> addAttention(@PathVariable UUID id_appointment,
      @RequestBody AttentionResponseDTO attentionDTO)
      throws NotFoundException {
    Tuple<AttentionResponseDTO, UUID> response = attentionService.saveAttention(id_appointment, attentionDTO);
    URI location = UriGeneric.CreateUri("/{id_attention}", response.getSecond());
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.CREATED.value())
        .message("Atención registrada correctamente")
        .data(response.getFirst())
        .build();
    return ResponseEntity.created(location).body(successMessage);
  }

  @Operation(summary = "Eliminar una atención médica", description = "Marca como eliminada una atención médica por su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atención eliminada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Atención no encontrada", content = @Content),
      @ApiResponse(responseCode = "400", description = "Atención ya eliminada", content = @Content)
  })
  @DeleteMapping("/{id_attention}")
  public ResponseEntity<?> deleteAttention(@PathVariable UUID id_attention)
      throws NotFoundException, BadRequestException {
    attentionService.deleteAttention(id_attention);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atención eliminada correctamente")
        .data("Actualiza la tabla")
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar todas las atenciones médicas", description = "Obtiene todas las atenciones registradas en el sistema con paginación.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atenciones obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/")
  public ResponseEntity<?> getAllAttentions(@RequestParam(defaultValue = "0") int page) {
    List<AttentionWithDoctorAndPatientDTO> attentions = attentionService.getListAttention(page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atenciones obtenidas correctamente")
        .data(attentions)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar atenciones por ID del paciente", description = "Obtiene todas las atenciones asociadas a un paciente usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atenciones del paciente obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/patient/{id_patient}")
  public ResponseEntity<?> getAttentionsByIdPatient(@PathVariable UUID id_patient,
      @RequestParam(defaultValue = "0") int page) {
    List<AttentionWithDoctorAndPatientDTO> attentions = attentionService.getListAttentionByIdPatient(id_patient, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atenciones del paciente obtenidas correctamente")
        .data(attentions)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar atenciones por DNI del paciente", description = "Obtiene todas las atenciones asociadas a un paciente usando su DNI.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atenciones del paciente obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/patient/dni/{dni}")
  public ResponseEntity<?> getAttentionsByDniPatient(@PathVariable String dni,
      @RequestParam(defaultValue = "0") int page) {
    List<AttentionWithDoctorAndPatientDTO> attentions = attentionService.getListAttentionByDniPatient(dni, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atenciones del paciente (por DNI) obtenidas correctamente")
        .data(attentions)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar atenciones por ID del doctor", description = "Obtiene todas las atenciones realizadas por un doctor usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atenciones del doctor obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/doctor/{id_doctor}")
  public ResponseEntity<?> getAttentionsByIdDoctor(@PathVariable UUID id_doctor,
      @RequestParam(defaultValue = "0") int page) {
    List<AttentionWithDoctorAndPatientDTO> attentions = attentionService.getListAttentionByIdDoctor(id_doctor, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atenciones del doctor obtenidas correctamente")
        .data(attentions)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar atenciones por CMP del doctor", description = "Obtiene todas las atenciones realizadas por un doctor usando su CMP.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Atenciones del doctor (por CMP) obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/doctor/cmp/{cmp}")
  public ResponseEntity<?> getAttentionsByCmpDoctor(@PathVariable String cmp,
      @RequestParam(defaultValue = "0") int page) {
    List<AttentionWithDoctorAndPatientDTO> attentions = attentionService.getListAttentionByCmpDoctor(cmp, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Atenciones del doctor (por CMP) obtenidas correctamente")
        .data(attentions)
        .build();
    return ResponseEntity.ok(successMessage);
  }

}
