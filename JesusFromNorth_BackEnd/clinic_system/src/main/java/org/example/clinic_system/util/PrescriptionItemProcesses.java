package org.example.clinic_system.util;

import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.model.Medicine;
import org.example.clinic_system.model.Prescription;
import org.example.clinic_system.model.PrescriptionItem;

public class PrescriptionItemProcesses {
    public static PrescriptionItem toPrescriptionItemProcesses(Prescription prescription, Medicine medicine, PrescriptionItemResponseDTO prescriptionItemResponseDTO) {
        return PrescriptionItem.builder()
                .prescription(prescription)
                .dose(prescriptionItemResponseDTO.getDose())
                .frequency(prescriptionItemResponseDTO.getFrequency())
                .duration(prescriptionItemResponseDTO.getDuration())
                .medicationFormat(prescriptionItemResponseDTO.getMedicationFormat())
                .medicine(medicine)
                .build();
    }
}
