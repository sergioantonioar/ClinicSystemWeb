package org.example.clinic_system.util;

import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Attention;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class AttentionProcesses {
    public static Attention ToAttention(Appointment appointment, AttentionResponseDTO attentionResponseDTO) {
        return Attention.builder()
                .attentionType(attentionResponseDTO.getAttentionType())
                .appointment(appointment)
                .diagnosis(attentionResponseDTO.getDiagnosis())
                .treatment(attentionResponseDTO.getTreatment())
                .build();
    }

    public static AttentionResponseDTO ToAttentionDTO(Attention attentionResponseDTO,List<PrescriptionItemResponseDTO> prescriptions) {
        return AttentionResponseDTO.builder()
                .attentionType(attentionResponseDTO.getAttentionType())
                .prescriptions(prescriptions) // Puede aver errores ya estoy usando las que paso el usuario,no las que estan en la bd
                .diagnosis(attentionResponseDTO.getDiagnosis())
                .treatment(attentionResponseDTO.getTreatment())
                .build();
    }

    public static AttentionWithDoctorAndPatientDTO toAttentionWithDoctorAndPatientDTO(Attention attention){
        return AttentionWithDoctorAndPatientDTO.builder()
                .diagnosis(attention.getDiagnosis())
                .treatment(attention.getTreatment())
                .attentionType(attention.getAttentionType())
                .doctorDTO(DoctorProcesses.CreateDoctorDTO(attention.getAppointment().getDoctor()))
                .patientDTO(PatientProcesses.createPatientDTO(attention.getAppointment().getPatient()))
                .date_attention(
                        Optional.ofNullable(attention.getAppointment().getDate_appointment())
                                .orElse(LocalDateTime.now())
                )
                .build();
    }

    public static List<AttentionWithDoctorAndPatientDTO> toListAttentionWithDoctorAndPatientDTO(List<Attention> attentions) {
        return attentions.stream().map(
                AttentionProcesses::toAttentionWithDoctorAndPatientDTO
        ).toList();
    }

}
