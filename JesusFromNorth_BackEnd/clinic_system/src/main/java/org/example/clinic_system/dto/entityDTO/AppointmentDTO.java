package org.example.clinic_system.dto.entityDTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Patient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AppointmentDTO {
    private UUID id_appointment;
    private LocalDateTime date_appointment;
    private LocalDateTime date_attention;
    private String description;
    private DoctorDTO doctor;
    private PatientDTO patient;
}
