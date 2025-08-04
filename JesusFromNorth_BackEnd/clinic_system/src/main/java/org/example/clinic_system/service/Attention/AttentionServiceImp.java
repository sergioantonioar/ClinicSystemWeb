package org.example.clinic_system.service.Attention;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.handler.BadRequestException;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.repository.AttentionRepository;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.service.ServiceAux.ServiceAuxPrescription.PrescriptionAuxService;
import org.example.clinic_system.util.AttentionProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttentionServiceImp implements AttentionService{

    private final AttentionRepository attentionRepository;
    private final AppointmentService appointmentService;
    private final PrescriptionAuxService prescriptionAuxService;

    @Value("${page-size}")
    private int size;

    @Override
    public Tuple<AttentionResponseDTO, UUID> saveAttention(UUID id_appointment, AttentionResponseDTO attentionDTO) throws NotFoundException {
        Appointment appointment = appointmentService.getAppointmentById(id_appointment);
        Attention attention = attentionRepository.save(AttentionProcesses.ToAttention(appointment, attentionDTO));
        prescriptionAuxService.savePrescription(attention,attentionDTO.getPrescriptions());
        return new Tuple<AttentionResponseDTO,UUID>(
                AttentionProcesses.ToAttentionDTO(attention,attentionDTO.getPrescriptions()),
                attention.getId_attention()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttention(int page) {
        return AttentionProcesses.toListAttentionWithDoctorAndPatientDTO(
                attentionRepository.findAll(
                        PageRequest.of(page,size)
                ).getContent()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdPatient(UUID id_patient, int page) {
        return AttentionProcesses.toListAttentionWithDoctorAndPatientDTO(
                attentionRepository.findAllByIdPatient(
                        id_patient,
                        PageRequest.of(page,size)
                ).getContent()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdDoctor(UUID id_doctor, int page) {
        return AttentionProcesses.toListAttentionWithDoctorAndPatientDTO(
                attentionRepository.findAllByIdDoctor(
                        id_doctor,
                        PageRequest.of(page,size)
                ).getContent()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByDniPatient(String Dni_patient, int page) {
        return AttentionProcesses.toListAttentionWithDoctorAndPatientDTO(
                attentionRepository.findAllByDniPatient(
                        Dni_patient,
                        PageRequest.of(page,size)
                ).getContent()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByCmpDoctor(String Cmp_doctor, int page) {
        return AttentionProcesses.toListAttentionWithDoctorAndPatientDTO(
                attentionRepository.findAllByCmpDoctor(
                        Cmp_doctor,
                        PageRequest.of(page,size)
                ).getContent()
        );
    }


    @Override
    public Attention getAttentionById(UUID id) throws NotFoundException {
        return attentionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Atencion no encontrada")
        );
    }

    @Override
    public void deleteAttention(UUID id_attention) throws NotFoundException, BadRequestException {
        Attention attention = attentionRepository.findById(id_attention).orElseThrow(
                () -> new NotFoundException("Attention no encontrada")
        );
        if(attention.getIs_deleted() == true){
            throw new BadRequestException("La atencion ya esta eliminada");
        }
        attention.setIs_deleted(true);
        attentionRepository.save(attention);
    }

}
