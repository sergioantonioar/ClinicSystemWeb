package org.example.clinic_system.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicineResponseDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate medicine_date;
    private String medicine_name;
    private String medicine_description;
    private String medicine_type;
    private String medicine_side_effect;
}
