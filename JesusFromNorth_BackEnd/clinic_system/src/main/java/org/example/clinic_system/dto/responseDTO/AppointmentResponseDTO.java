package org.example.clinic_system.dto.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentResponseDTO {
    private LocalDateTime date_appointment;
    private LocalDateTime date_attention;
    private String description;
}
