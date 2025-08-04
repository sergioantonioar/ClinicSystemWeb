package org.example.clinic_system.service.Prescription;



import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.model.Prescription;



import java.util.UUID;

public interface PrescriptionService {
    Prescription savePrescription(Attention attention) throws NotFoundException;
    Prescription getPrescription(UUID id_prescription) throws NotFoundException;
}
