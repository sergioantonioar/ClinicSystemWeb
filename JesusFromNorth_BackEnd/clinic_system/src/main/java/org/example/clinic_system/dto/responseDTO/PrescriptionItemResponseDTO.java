package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.MedicationFormat;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrescriptionItemResponseDTO {
    private UUID id_medicine;
    private Float dose;
    private Float frequency;
    private Byte duration;
    private MedicationFormat medicationFormat;
}
