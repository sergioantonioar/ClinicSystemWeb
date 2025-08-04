package org.example.clinic_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.util.ExcelExporter;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patient")
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @Operation(summary = "Crear paciente", description = "Registra un nuevo paciente asociado a un administrador.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Paciente creado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
  })
  @PostMapping("/{id_admin}")
  public ResponseEntity<?> createPatient(
      @RequestBody PatientResponseDTO patientResponseDTO,
      @PathVariable UUID id_admin) throws NotFoundException {
    Tuple<PatientResponseDTO, UUID> patient = patientService.savePatient(id_admin, patientResponseDTO);
    URI location = UriGeneric.CreateUri("/{id_patient}", patient.getSecond());
    return ResponseEntity.created(location).body(SuccessMessage.<PatientResponseDTO>builder()
        .status(HttpStatus.CREATED.value())
        .message("Paciente creado con éxito")
        .data(patient.getFirst())
        .build());
  }

  @Operation(summary = "Buscar paciente por ID", description = "Devuelve los datos de un paciente según su identificador único.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content)
  })
  @GetMapping("/{id_patient}")
  public ResponseEntity<?> getPatientById(
      @PathVariable UUID id_patient) throws NotFoundException {
    PatientDTO patientDTO = patientService.getPatientDTOById(id_patient);
    return ResponseEntity.ok().body(
        SuccessMessage.<PatientDTO>builder()
            .status(HttpStatus.OK.value())
            .message("Paciente encontrado con éxito")
            .data(patientDTO)
            .build());
  }

  @Operation(summary = "Buscar paciente por DNI", description = "Devuelve los datos de un paciente usando su número de DNI.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content)
  })
  @GetMapping("/")
  public ResponseEntity<?> getPatientById(@RequestParam String dni) throws NotFoundException {
    PatientDTO patientDTO = patientService.getPatientDTOByDni(dni);
    return ResponseEntity.ok().body(
        SuccessMessage.<PatientDTO>builder()
            .status(HttpStatus.OK.value())
            .message("Paciente encontrado con éxito")
            .data(patientDTO)
            .build());
  }

  @Operation(summary = "Actualizar paciente", description = "Actualiza los datos de un paciente existente.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Paciente actualizado con éxito"),
      @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content)
  })
  @PatchMapping("/{id_patient}")
  public ResponseEntity<?> updatePatient(
      @RequestBody PatientResponseDTO patientResponseDTO,
      @PathVariable UUID id_patient) throws NotFoundException {
    patientService.updatePatient(id_patient, patientResponseDTO);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Eliminar paciente", description = "Elimina un paciente del sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Paciente eliminado con éxito"),
      @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content)
  })
  @DeleteMapping("/{id_patient}")
  public ResponseEntity<?> deletePatient(
      @PathVariable UUID id_patient) throws NotFoundException {
    patientService.deletePatient(id_patient);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Listar pacientes", description = "Obtiene una lista paginada de todos los pacientes.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/list")
  public ResponseEntity<?> getAllPatients(
      @Parameter(description = "Número de página a consultar", example = "0") @RequestParam int page) {
    List<PatientDTO> ListPatientDTO = patientService.getAllPatients(page);
    return ResponseEntity.ok().body(
        SuccessMessage.<List<PatientDTO>>builder()
            .status(HttpStatus.OK.value())
            .message("Lista de los pacientes de página: " + page)
            .data(ListPatientDTO)
            .build());
  }

  @Operation(summary = "Listar pacientes por apellido", description = "Obtiene una lista paginada de pacientes filtrada por apellido.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/list/")
  public ResponseEntity<?> getAllPatientsByLastName(
      @Parameter(description = "Apellido del paciente", example = "Gonzalez") @RequestParam String lastName,
      @Parameter(description = "Número de página", example = "0") @RequestParam int page) {
    List<PatientDTO> ListPatientDTO = patientService.getAllPatientsByLastName(lastName, page);
    return ResponseEntity.ok().body(
        SuccessMessage.<List<PatientDTO>>builder()
            .status(HttpStatus.OK.value())
            .message("Lista de los pacientes de página: " + page)
            .data(ListPatientDTO)
            .build());
  }

  @Operation(summary = "Exportar pacientes en excel", description = "Obtiene una archivo excel de pacientes.")
  @ApiResponse(responseCode = "200", description = "Excel exportado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/export/excel")
  public ResponseEntity<byte[]> exportPatientsToExcel() {
    try {
      List<PatientDTO> patients = patientService.getAllPatients(0); // Puedes paginar según necesidad
      ByteArrayInputStream stream = ExcelExporter.patientsToExcel(patients);

      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attachment; filename=pacientes.xlsx");

      return ResponseEntity
          .ok()
          .headers(headers)
          .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
          .body(stream.readAllBytes());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}
