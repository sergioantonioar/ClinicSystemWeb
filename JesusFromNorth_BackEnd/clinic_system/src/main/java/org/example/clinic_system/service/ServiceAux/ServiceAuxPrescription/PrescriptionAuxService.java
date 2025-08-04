package org.example.clinic_system.service.ServiceAux.ServiceAuxPrescription;

import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Attention;

import java.util.List;
import java.util.UUID;

public interface PrescriptionAuxService {
    void savePrescription(Attention attention,List<PrescriptionItemResponseDTO> prescriptions) throws NotFoundException;
}
