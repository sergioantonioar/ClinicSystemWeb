package org.example.clinic_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.MedicineDTO;
import org.example.clinic_system.dto.responseDTO.MedicineResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Medicine.MedicineService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("medicine")
@RequiredArgsConstructor
public class MedicineController {

  private final MedicineService medicineService;

  @Operation(summary = "Crear medicamento", description = "Registra un nuevo medicamento asociado a un administrador.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Medicamento creado con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
  })
  @PostMapping("/{id_admin}")
  public ResponseEntity<?> createMedicine(
      @RequestBody MedicineResponseDTO medicineResponseDTO,
      @PathVariable UUID id_admin) throws NotFoundException {
    Tuple<MedicineResponseDTO, UUID> medicine = medicineService.SaveMedicine(medicineResponseDTO, id_admin);
    URI location = UriGeneric.CreateUri("/{id_medicine}", medicine.getSecond());
    return ResponseEntity.created(location).body(SuccessMessage.<MedicineResponseDTO>builder()
        .status(HttpStatus.CREATED.value())
        .message("Medicamento creado con exito")
        .data(medicine.getFirst())
        .build());
  }

  @Operation(summary = "Buscar medicamento por ID", description = "Devuelve los datos de un medicamento por su identificador único.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Medicamento encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Medicamento no encontrado", content = @Content)
  })
  @GetMapping("/{id_medicine}")
  public ResponseEntity<?> getMedicineById(
      @PathVariable UUID id_medicine) throws NotFoundException {
    MedicineDTO medicineDTO = medicineService.getMedicineDTOById(id_medicine);
    return ResponseEntity.ok().body(
        SuccessMessage.<MedicineDTO>builder()
            .status(HttpStatus.OK.value())
            .message("Medicamento encontrado con exito")
            .data(medicineDTO)
            .build());
  }

  @Operation(summary = "Buscar medicamento por nombre", description = "Devuelve los datos de un medicamento usando su nombre.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Medicamento encontrado", content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
      @ApiResponse(responseCode = "404", description = "Medicamento no encontrado", content = @Content)
  })
  @GetMapping("/")
  public ResponseEntity<?> getMedicineByName(@RequestParam String name_medicine) throws NotFoundException {
    MedicineDTO medicineDTO = medicineService.getMedicineDTOByName(name_medicine);
    return ResponseEntity.ok().body(
        SuccessMessage.<MedicineDTO>builder()
            .status(HttpStatus.OK.value())
            .message("Medicamento encontrado con exito")
            .data(medicineDTO)
            .build());
  }

  @Operation(summary = "Actualizar medicamento", description = "Actualiza los datos de un medicamento existente.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Medicamento actualizado con éxito"),
      @ApiResponse(responseCode = "404", description = "Medicamento no encontrado", content = @Content)
  })
  @PatchMapping("/{id_medicine}")
  public ResponseEntity<?> updateMedicine(
      @RequestBody MedicineResponseDTO medicineResponseDTO,
      @PathVariable UUID id_medicine) throws NotFoundException {
    medicineService.updateMedicine(id_medicine, medicineResponseDTO);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Eliminar medicamento", description = "Elimina un medicamento del sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Medicamento eliminado con éxito"),
      @ApiResponse(responseCode = "404", description = "Medicamento no encontrado", content = @Content)
  })
  @DeleteMapping("/{id_medicine}")
  public ResponseEntity<?> deleteMedicine(
      @PathVariable UUID id_medicine) throws NotFoundException {
    medicineService.deleteMedicine(id_medicine);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Listar medicamentos", description = "Obtiene una lista paginada de todos los medicamentos.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/list")
  public ResponseEntity<?> getAllMedicines(@RequestParam int page) {
    List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicines(page);
    return ResponseEntity.ok().body(
        SuccessMessage.<List<MedicineDTO>>builder()
            .status(HttpStatus.OK.value())
            .message("Lista de las medicinas de pagina: " + page)
            .data(listMedicineDTO)
            .build());
  }

  @Operation(summary = "Listar medicamentos por tipo", description = "Obtiene una lista paginada de medicamentos filtrada por tipo.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/list/by-type")
  public ResponseEntity<?> getAllMedicinesByType(
      @RequestParam String type_medicine,
      @RequestParam int page) {
    List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicinesByType(type_medicine, page);
    return ResponseEntity.ok().body(
        SuccessMessage.<List<MedicineDTO>>builder()
            .status(HttpStatus.OK.value())
            .message("Lista de las medicinas de pagina: " + page)
            .data(listMedicineDTO)
            .build());
  }

  @Operation(summary = "Listar medicamentos por fecha", description = "Obtiene una lista paginada de medicamentos según la fecha.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito", content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
  @GetMapping("/list/by-date")
  public ResponseEntity<?> getAllMedicinesByDate(
      @RequestParam LocalDate date,
      @RequestParam int page) {
    List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicinesByDate(date, page);
    return ResponseEntity.ok().body(
        SuccessMessage.<List<MedicineDTO>>builder()
            .status(HttpStatus.OK.value())
            .message("Lista de las medicinas de pagina: " + page)
            .data(listMedicineDTO)
            .build());
  }

}
