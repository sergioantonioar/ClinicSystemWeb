package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Patient;

import java.time.LocalDateTime;

public class AppointmentProcesses {
    public static Appointment CreateAppointment(AppointmentResponseDTO appointmentDTO, Doctor doctor, Patient patient, Admin admin) {
        return Appointment.builder()
                .date_appointment(LocalDateTime.now())
                .date_attention(appointmentDTO.getDate_appointment())
                .description(appointmentDTO.getDescription())
                .admin(admin)
                .doctor(doctor)
                .patient(patient)
                .build();
    }

    public static AppointmentResponseDTO CreateAppointmentResponseDTO(Appointment appointment){
        return AppointmentResponseDTO.builder()
                .date_appointment(appointment.getDate_appointment())
                .date_attention(appointment.getDate_attention())
                .description(appointment.getDescription())
                .build();
    }

    public static AppointmentDTO CreateAppointmentDTO(Appointment appointment){
        return AppointmentDTO.builder()
                .id_appointment(appointment.getId_appointment())
                .date_appointment(appointment.getDate_appointment())
                .date_attention(appointment.getDate_attention())
                .description(appointment.getDescription())
                .doctor(
                        DoctorProcesses.CreateDoctorDTO(appointment.getDoctor())
                )
                .patient(
                        PatientProcesses.createPatientDTO(appointment.getPatient())
                )
                .build();
    }

    public static Appointment UpdateAppointment(Appointment appointment, AppointmentResponseDTO appointmentDTO) {
        if (appointmentDTO.getDate_appointment() != null)
            appointment.setDate_appointment(appointmentDTO.getDate_appointment());

        if (appointmentDTO.getDate_attention() != null)
            appointment.setDate_attention(appointmentDTO.getDate_attention());

        if (appointmentDTO.getDescription() != null)
            appointment.setDescription(appointmentDTO.getDescription());
        return appointment;
    }

}
