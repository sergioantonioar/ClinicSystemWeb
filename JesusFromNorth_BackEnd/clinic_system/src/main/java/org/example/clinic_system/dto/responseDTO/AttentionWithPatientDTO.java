package org.example.clinic_system.dto.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.model.enums.AttentionType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttentionWithPatientDTO {
    private String diagnosis;
    private String treatment;
    private AttentionType attentionType;
    private PatientDTO patientDTO;
    private LocalDateTime date_attention;
}
