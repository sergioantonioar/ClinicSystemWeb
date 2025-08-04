package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseWithIDSpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Tuple <DoctorResponseDTO, UUID> SaveDoctorWithUsername(RegisterDoctorDTO registerDoctorDTO, UUID id_admin, UUID id_specialist) throws NotFoundException;
    Tuple <DoctorResponseDTO, UUID> SaveDoctorWithoutUsername(RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO, UUID id_admin, UUID id_specialist) throws NotFoundException;
    DoctorDTO getDoctorDTOById(UUID id_doctor) throws NotFoundException;
    List<DoctorDTO> getAllDoctors(int page);
    List<DoctorDTO> getAllDoctorsBySpecialist(UUID id_specialist,int page);
    void updateDoctor(UUID id_doctor, DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException;
    void deleteDoctor(UUID id_doctor) throws NotFoundException;
    Doctor getDoctorById (UUID id_doctor) throws NotFoundException;
    DoctorDTO getDoctorByCmp(String cmp) throws NotFoundException;
    DoctorDTO getDoctorByDni(String cmp) throws NotFoundException;
    Doctor getDoctorByIdUser(UUID id_user) throws NotFoundException;
}
