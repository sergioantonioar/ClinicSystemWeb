package org.example.clinic_system.dto.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicineDTO {
    private UUID id_medicine;
    private String medicine_name;
    private String medicine_description;
    private String medicine_type;
    private String medicine_side_effect;
    private LocalDate medicine_date;
}
