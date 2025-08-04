package org.example.clinic_system.repository;

import org.example.clinic_system.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    @Query("SELECT m FROM Medicine m WHERE m.id_medicine=?1 and m.is_deleted=false")
    Optional<Medicine> findMedicineById(UUID medicineId);

    @Query("SELECT m FROM Medicine m WHERE m.medicine_name=?1 and m.is_deleted=false")
    Optional<Medicine> findMedicineByMedicineName(String medicineName);

    @Query("SELECT m FROM Medicine m WHERE m.is_deleted=false")
    Page<Medicine> findAllMedicines(Pageable pageable);

    @Query("SELECT m FROM Medicine m WHERE m.medicine_type=?1 and m.is_deleted=false")
    Page<Medicine> findAllMedicinesByType(String medicineType,Pageable pageable);

    @Query("SELECT m FROM Medicine m WHERE m.medicine_date=?1 and m.is_deleted=false")
    Page<Medicine> findAllMedicinesByDate(LocalDate date, Pageable pageable);
}
