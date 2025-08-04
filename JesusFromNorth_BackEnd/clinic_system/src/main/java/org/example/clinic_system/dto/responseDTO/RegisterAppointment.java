package org.example.clinic_system.dto.responseDTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAppointment {
    private UUID id_admin;
    private UUID id_doctor;
    private String dni_patient;
    private AppointmentResponseDTO responseDTO;
}
