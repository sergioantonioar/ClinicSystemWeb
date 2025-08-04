package org.example.clinic_system.service.PrescriptionItem;

import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Prescription;



public interface PrescriptionItemService {
    void save(Prescription prescription, PrescriptionItemResponseDTO prescriptionItemResponseDTO) throws NotFoundException;
}
