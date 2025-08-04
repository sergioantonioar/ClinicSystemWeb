package org.example.clinic_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterAppointment;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @Operation(summary = "Registrar una nueva cita médica", description = "Crea una nueva cita médica asignando un administrador, un doctor y un paciente. Retorna los datos de la cita creada.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Cita creada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Administrador, doctor o paciente no encontrado", content = @Content)
  })
  @PostMapping("/")
  public ResponseEntity<?> addAppointment(@RequestBody RegisterAppointment registerAppointment)
      throws NotFoundException {
    Tuple<AppointmentResponseDTO, UUID> response = appointmentService.saveAppointment(
        registerAppointment.getId_admin(),
        registerAppointment.getId_doctor(),
        registerAppointment.getDni_patient(),
        registerAppointment.getResponseDTO());
    URI location = UriGeneric.CreateUri("/{id_appointment}", response.getSecond());
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Se creó la cita correctamente")
        .data(response.getFirst())
        .build();
    return ResponseEntity.created(location).body(successMessage);
  }

  @Operation(summary = "Actualizar una cita médica", description = "Actualiza los datos de una cita médica existente por su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cita actualizada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
  })
  @PatchMapping("/{id_appointment}")
  public ResponseEntity<?> updateAppointment(@PathVariable UUID id_appointment,
      @RequestBody AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException {
    appointmentService.updateAppointment(id_appointment, appointmentResponseDTO);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Se actualizó la cita correctamente")
        .data("Actualiza la tabla")
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Eliminar una cita médica", description = "Elimina una cita médica por su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cita eliminada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
  })
  @DeleteMapping("/{id_appointment}")
  public ResponseEntity<?> deleteAppointment(@PathVariable UUID id_appointment) throws NotFoundException {
    appointmentService.deleteAppointment(id_appointment);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Se eliminó la cita correctamente")
        .data("Actualiza la tabla")
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Obtener una cita médica por ID", description = "Retorna los detalles de una cita médica específica usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cita encontrada correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
  })
  @GetMapping("/{id_appointment}")
  public ResponseEntity<?> getAppointment(@PathVariable UUID id_appointment) throws NotFoundException {
    AppointmentDTO appointmentDTO = appointmentService.getAppointmentDTOById(id_appointment);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Se encontró la cita correctamente")
        .data(appointmentDTO)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar citas por ID del paciente", description = "Obtiene todas las citas asociadas a un paciente usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citas del paciente obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/patient/{id_patient}")
  public ResponseEntity<?> getAllAppointmentsByIdPatient(@PathVariable UUID id_patient,
      @RequestParam(defaultValue = "0") int page) throws NotFoundException {
    List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByIdPatient(id_patient, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Citas del paciente obtenidas correctamente")
        .data(appointments)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar citas por ID del doctor", description = "Obtiene todas las citas asignadas a un doctor usando su ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citas del doctor obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/doctor/{id_doctor}")
  public ResponseEntity<?> getAllAppointmentsByIdDoctor(@PathVariable UUID id_doctor,
      @RequestParam(defaultValue = "0") int page) throws NotFoundException {
    List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByIdDoctor(id_doctor, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Citas del doctor obtenidas correctamente")
        .data(appointments)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar citas por CMP del doctor", description = "Obtiene todas las citas asignadas a un doctor usando su CMP.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citas del doctor (por CMP) obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/doctor/cmp/{cmp}")
  public ResponseEntity<?> getAllAppointmentsByCmpDoctor(@PathVariable String cmp,
      @RequestParam(defaultValue = "0") int page) throws NotFoundException {
    List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByCmpDoctor(cmp, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Citas del doctor (por CMP) obtenidas correctamente")
        .data(appointments)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar citas por DNI del paciente", description = "Obtiene todas las citas asociadas a un paciente usando su DNI.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citas del paciente (por DNI) obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/patient/dni/{dni}")
  public ResponseEntity<?> getAllAppointmentsByDniPatient(@PathVariable String dni,
      @RequestParam(defaultValue = "0") int page) throws NotFoundException {
    List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDniPatient(dni, page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Citas del paciente (por DNI) obtenidas correctamente")
        .data(appointments)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Listar todas las citas", description = "Obtiene todas las citas registradas en el sistema con paginación.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Todas las citas obtenidas correctamente", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  })
  @GetMapping("/")
  public ResponseEntity<?> getAllAppointments(@RequestParam(defaultValue = "0") int page) {
    List<AppointmentDTO> appointments = appointmentService.getAllAppointments(page);
    SuccessMessage<?> successMessage = SuccessMessage.builder()
        .status(HttpStatus.OK.value())
        .message("Todas las citas obtenidas correctamente")
        .data(appointments)
        .build();
    return ResponseEntity.ok(successMessage);
  }

  @Operation(summary = "Generar boleta en PDF de una cita médica", description = "Genera una boleta en formato PDF para una cita específica utilizando JasperReports.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Boleta generada correctamente", content = @Content(mediaType = "application/pdf")),
      @ApiResponse(responseCode = "404", description = "Cita médica no encontrada")
  })
  @GetMapping("/invoice/{id_appointment}")
  public ResponseEntity<byte[]> generateInvoice(@PathVariable UUID id_appointment)
      throws NotFoundException, JRException {
    byte[] pdfBytes = appointmentService.generateInvoice(id_appointment);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDisposition(ContentDisposition
        .inline()
        .filename("boleta_" + id_appointment + ".pdf")
        .build());

    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
  }
}
