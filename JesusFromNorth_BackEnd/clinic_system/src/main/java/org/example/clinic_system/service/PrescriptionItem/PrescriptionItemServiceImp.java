package org.example.clinic_system.service.PrescriptionItem;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Medicine;
import org.example.clinic_system.model.Prescription;
import org.example.clinic_system.model.PrescriptionItem;
import org.example.clinic_system.repository.PrescriptionItemRepository;
import org.example.clinic_system.service.Medicine.MedicineService;
import org.example.clinic_system.util.PrescriptionItemProcesses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionItemServiceImp implements PrescriptionItemService{

    private final PrescriptionItemRepository prescriptionItemRepository;
    private final MedicineService medicineService;


    @Override
    public void save(Prescription prescription, PrescriptionItemResponseDTO prescriptionItemResponseDTO) throws NotFoundException {
        Medicine medicine = medicineService.getMedicineById(prescriptionItemResponseDTO.getId_medicine());
        PrescriptionItem prescriptionItem = PrescriptionItemProcesses.toPrescriptionItemProcesses(prescription,medicine,prescriptionItemResponseDTO);
        prescriptionItemRepository.save(prescriptionItem);
    }
}
