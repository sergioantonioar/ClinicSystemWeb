package org.example.clinic_system.service.Prescription;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.model.Prescription;
import org.example.clinic_system.repository.PrescriptionRepository;
import org.example.clinic_system.service.Attention.AttentionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImp implements  PrescriptionService{

    private final PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription savePrescription(Attention attention) throws NotFoundException {
        Prescription prescription = Prescription.builder()
                .attention(attention)
                .date(LocalDate.now())
                .build();
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescription(UUID id_prescription) throws NotFoundException {
        return prescriptionRepository.findById(id_prescription)
                .orElseThrow(() -> new NotFoundException("Prescripcion no encontrada"));
    }
}
