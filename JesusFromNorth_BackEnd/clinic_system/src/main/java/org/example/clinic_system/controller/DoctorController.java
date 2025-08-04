package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.*;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.util.ExcelExporter;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "doctor")
@RequiredArgsConstructor
@Tag(name = "Doctor", description = "Endpoints para gestión de doctores")
public class DoctorController {

  private final DoctorService doctorService;

  @Operation(summary = "Crear doctor con usuario y contraseña personalizados", description = "Crea un doctor asignando un administrador y una especialidad, con username y contraseña definidos por el usuario.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Doctor creado exitosamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Administrador o especialidad no encontrada", content = @Content)
  })
  @PostMapping("/save/assignAdmin/{adminId}/assignSpecialty/{specialistId}")
  public ResponseEntity<?> saveDoctorWithUsername(
      @RequestBody RegisterDoctorDTO registerDoctorDTO,
      @PathVariable("adminId") UUID adminId,
      @PathVariable("specialistId") UUID specialistId) throws NotFoundException {

    Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithUsername(registerDoctorDTO, adminId,
        specialistId);

    SuccessMessage<DoctorResponseDTO> successMessage = SuccessMessage.<DoctorResponseDTO>builder()
        .status(HttpStatus.OK.value())
        .message("Se agregó el doctor")
        .data(response.getFirst())
        .build();

    URI location = UriGeneric.CreateUri("/{doctorId}", response.getSecond());
    return ResponseEntity.created(location).body(successMessage);
  }

  @Operation(summary = "Crear doctor con DNI como usuario", description = "Crea un doctor asignando un administrador y una especialidad. El DNI será usado como username y password.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor creado correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Administrador o especialidad no encontrada", content = @Content)
  })
  @PostMapping("/{adminId}/{specialistId}")
  public ResponseEntity<?> saveDoctor(
      @RequestBody RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,
      @PathVariable("adminId") UUID adminId,
      @PathVariable("specialistId") UUID specialistId) throws NotFoundException {
    Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithoutUsername(registerDoctorNoUsernameDTO,
        adminId, specialistId);
    return ResponseEntity.ok(SuccessMessage.<Tuple<DoctorResponseDTO, UUID>>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor creado con DNI como nombre de usuario.")
        .data(response)
        .build());
  }

  @Operation(summary = "Obtener doctor por ID", description = "Devuelve los datos de un doctor a partir de su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Doctor no encontrado", content = @Content)
  })
  @GetMapping("/{doctorId}")
  public ResponseEntity<?> getDoctorById(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
    DoctorDTO doctor = doctorService.getDoctorDTOById(doctorId);
    return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor obtenido con éxito.")
        .data(doctor)
        .build());
  }

  @Operation(summary = "Obtener todos los doctores", description = "Devuelve una lista paginada de todos los doctores registrados. El parámetro 'page' indica la página deseada.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de doctores obtenida", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/list")
  public ResponseEntity<?> getAllDoctors(@RequestParam int page) {
    List<DoctorDTO> doctors = doctorService.getAllDoctors(page);
    return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
        .status(HttpStatus.OK.value())
        .message("Lista de todos los doctores obtenida con éxito.")
        .data(doctors)
        .build());
  }

  @Operation(summary = "Obtener doctores por especialidad", description = "Devuelve una lista paginada de doctores filtrada por especialidad. El parámetro 'page' indica la página deseada.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de doctores obtenida", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/list/{specialistId}")
  public ResponseEntity<?> getAllDoctorsBySpecialty(@PathVariable UUID specialistId, @RequestParam int page) {
    List<DoctorDTO> doctors = doctorService.getAllDoctorsBySpecialist(specialistId, page);
    return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
        .status(HttpStatus.OK.value())
        .message("Lista de todos los doctores obtenida con éxito.")
        .data(doctors)
        .build());
  }

  @Operation(summary = "Actualizar doctor", description = "Actualiza los datos de un doctor existente.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor actualizado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Doctor no encontrado", content = @Content)
  })
  @PatchMapping("/{doctorId}")
  public ResponseEntity<?> updateDoctor(
      @PathVariable("doctorId") UUID doctorId,
      @RequestBody DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException {
    doctorService.updateDoctor(doctorId, doctorResponseDTO);
    return ResponseEntity.ok(SuccessMessage.<String>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor actualizado con éxito.")
        .data("Actualiza la lista")
        .build());
  }

  @Operation(summary = "Eliminar doctor", description = "Elimina un doctor del sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor eliminado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Doctor no encontrado", content = @Content)
  })
  @DeleteMapping("/{doctorId}")
  public ResponseEntity<?> deleteDoctor(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
    doctorService.deleteDoctor(doctorId);
    return ResponseEntity.ok(SuccessMessage.<String>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor eliminado con éxito.")
        .data("Se borro adecuadamente el doctor")
        .build());
  }

  @Operation(summary = "Buscar doctor por CMP", description = "Devuelve los datos de un doctor usando su número de colegiatura (CMP).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Doctor no encontrado por CMP", content = @Content)
  })
  @GetMapping("/getCMP")
  public ResponseEntity<?> getDoctorByCmp(@RequestParam String cmp) throws NotFoundException {
    DoctorDTO doctor = doctorService.getDoctorByCmp(cmp);
    return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor obtenido con éxito por CMP.")
        .data(doctor)
        .build());
  }

  @Operation(summary = "Buscar doctor por DNI", description = "Devuelve los datos de un doctor usando su DNI.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Doctor encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Doctor no encontrado por DNI", content = @Content)
  })
  @GetMapping("/getDNI")
  public ResponseEntity<?> getDoctorByDni(@RequestParam String dni) throws NotFoundException {
    DoctorDTO doctor = doctorService.getDoctorByDni(dni);
    return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
        .status(HttpStatus.OK.value())
        .message("Doctor obtenido con éxito por DNI.")
        .data(doctor)
        .build());
  }

  @Operation(summary = "Exportar doctores en excel", description = "Obtiene una archivo excel de doctores.")
  @ApiResponse(responseCode = "200", description = "Excel exportado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/export/excel")
  public ResponseEntity<byte[]> exportDoctorsToExcel() {
    try {
      List<DoctorDTO> doctors = doctorService.getAllDoctors(0); // Puedes paginar según necesidad
      ByteArrayInputStream stream = ExcelExporter.doctorsToExcel(doctors);

      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Disposition", "attachment; filename=doctores.xlsx");

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
