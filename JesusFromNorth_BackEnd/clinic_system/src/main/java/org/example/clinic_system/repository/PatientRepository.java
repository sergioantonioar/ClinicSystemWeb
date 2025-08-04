package org.example.clinic_system.repository;

import org.example.clinic_system.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query("SELECT p FROM Patient p WHERE p.dni=?1 and p.is_deleted=false")
    Optional<Patient> findByDni(String dni);

    @Query("SELECT p FROM Patient p WHERE p.id_patient=?1 and p.is_deleted=false")
    Optional<Patient> findByIdPatient(UUID id_patient);

    @Query("SELECT p FROM Patient p WHERE p.is_deleted=false")
    Page<Patient> findAllPatients(Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.last_name=?1 AND p.is_deleted=false")
    Page<Patient> findAllPatientsByLastName(String lastName, Pageable pageable);
}
