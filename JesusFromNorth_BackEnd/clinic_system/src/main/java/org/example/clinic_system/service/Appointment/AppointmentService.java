package org.example.clinic_system.service.Appointment;

import net.sf.jasperreports.engine.JRException;
import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.util.Tuple;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    Tuple<AppointmentResponseDTO,UUID> saveAppointment(UUID id_admin,UUID id_doctor,String dni_patient,AppointmentResponseDTO responseDTO) throws NotFoundException;
    void updateAppointment(UUID id_appointment, AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException;
    AppointmentDTO getAppointmentDTOById(UUID id_appointment) throws NotFoundException;
    Appointment getAppointmentById(UUID id_appointment) throws NotFoundException;
    List<AppointmentDTO> getAllAppointmentsByIdPatient(UUID id_patient,int page) throws NotFoundException;
    List<AppointmentDTO> getAllAppointmentsByIdDoctor(UUID id_doctor,int page) throws NotFoundException;
    List<AppointmentDTO> getAllAppointmentsByCmpDoctor(String cmp,int page) throws NotFoundException;
    List<AppointmentDTO> getAllAppointmentsByDniPatient(String dni,int page) throws NotFoundException;
    List<AppointmentDTO> getAllAppointments(int page);
    void deleteAppointment(UUID id_appointment) throws NotFoundException;
    byte[] generateInvoice(UUID id_appointment) throws NotFoundException, JRException;
}
