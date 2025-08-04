package org.example.clinic_system.repository;

import org.example.clinic_system.model.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, UUID> {

    @Query("SELECT p from PrescriptionItem p WHERE p.medicine.id_medicine = ?1")
    List<PrescriptionItem> findAllByIdMedicine(UUID medicine_id); //Lista de PrescripItem por medicamento

}
