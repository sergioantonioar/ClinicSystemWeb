package org.example.clinic_system.service.Medicine;

import org.example.clinic_system.dto.entityDTO.MedicineDTO;
import org.example.clinic_system.dto.responseDTO.MedicineResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Medicine;
import org.example.clinic_system.util.Tuple;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicineService {
    Tuple<MedicineResponseDTO, UUID> SaveMedicine(MedicineResponseDTO medicineResponseDTO, UUID id_admin) throws NotFoundException;
    MedicineDTO getMedicineDTOById(UUID id_medicine) throws NotFoundException;
    MedicineDTO getMedicineDTOByName(String medicine_name) throws NotFoundException;
    Medicine getMedicineById(UUID id_medicine) throws NotFoundException;
    Medicine getMedicineByName(String medicine_name) throws NotFoundException;
    List<MedicineDTO> getAllMedicines(int page);
    List<MedicineDTO> getAllMedicinesByType(String medicine_type, int page);
    List<MedicineDTO> getAllMedicinesByDate(LocalDate date, int page);
    void updateMedicine(UUID id_medicine, MedicineResponseDTO medicineResponseDTO) throws NotFoundException;
    void deleteMedicine(UUID id_medicine) throws NotFoundException;
}
