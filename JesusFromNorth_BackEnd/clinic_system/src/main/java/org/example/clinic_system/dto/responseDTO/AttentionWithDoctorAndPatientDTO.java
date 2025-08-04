package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.model.enums.AttentionType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttentionWithDoctorAndPatientDTO {
    private String diagnosis;
    private String treatment;
    private AttentionType attentionType;
    private DoctorDTO doctorDTO;
    private PatientDTO patientDTO;
    private LocalDateTime date_attention;
}
