package org.example.clinic_system.service.Attention;

import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.handler.BadRequestException;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface AttentionService {

    //Tarea 1: terminar la logica de atencion
    Tuple<AttentionResponseDTO, UUID> saveAttention(UUID id_appointment,AttentionResponseDTO attentionDTO) throws NotFoundException;

    List<AttentionWithDoctorAndPatientDTO> getListAttention(int page);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdPatient(UUID id_patient,int page);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdDoctor(UUID id_doctor,int page);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByDniPatient(String Dni_patient,int page);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByCmpDoctor(String Cmp_doctor,int page);

    Attention getAttentionById(UUID id) throws NotFoundException;

    void deleteAttention(UUID id_attention) throws NotFoundException, BadRequestException;

}
