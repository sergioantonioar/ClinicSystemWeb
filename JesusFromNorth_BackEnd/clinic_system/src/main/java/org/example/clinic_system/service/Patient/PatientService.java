package org.example.clinic_system.service.Patient;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;


public interface PatientService {

    Tuple<PatientResponseDTO,UUID> savePatient(UUID id_admin,PatientResponseDTO patientResponseDTO) throws NotFoundException;

    PatientDTO getPatientDTOById(UUID id_patient) throws NotFoundException;

    PatientDTO getPatientDTOByDni(String dni) throws NotFoundException;

    Patient getPatientById(UUID id_patient) throws NotFoundException;

    Patient getPatientByDni(String dni_patient) throws NotFoundException;

    void updatePatient(UUID id_patient, PatientResponseDTO patientResponseDTO) throws NotFoundException;

    void deletePatient(UUID id_patient) throws NotFoundException;

    List<PatientDTO> getAllPatients(int page);

    List<PatientDTO> getAllPatientsByLastName(String lastName,int page);


}
