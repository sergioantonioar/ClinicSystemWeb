package org.example.clinic_system.service.ServiceAux.ServiceAuxPrescription;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.model.Prescription;
import org.example.clinic_system.repository.PrescriptionRepository;
import org.example.clinic_system.service.Attention.AttentionService;
import org.example.clinic_system.service.Prescription.PrescriptionService;
import org.example.clinic_system.service.PrescriptionItem.PrescriptionItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionAuxServiceImp implements PrescriptionAuxService {

    private final PrescriptionService prescriptionService;
    private final PrescriptionItemService prescriptionItemService;

    @Override
    public void savePrescription(Attention attention, List<PrescriptionItemResponseDTO> prescriptions) throws NotFoundException {
        Prescription prescription = prescriptionService.savePrescription(attention);
        for(PrescriptionItemResponseDTO prescriptionItemDTO : prescriptions) {
           prescriptionItemService.save(prescription, prescriptionItemDTO);
        }
    }
}
