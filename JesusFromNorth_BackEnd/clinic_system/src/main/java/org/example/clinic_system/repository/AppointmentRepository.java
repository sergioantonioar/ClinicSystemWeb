package org.example.clinic_system.repository;

import org.example.clinic_system.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("SELECT a FROM Appointment a WHERE a.id_appointment=?1 AND a.is_deleted=false")
    Optional<Appointment> findByIdAppointment(UUID id);

    @Query("SELECT a FROM Appointment a WHERE a.patient.dni = ?1 AND a.is_deleted=false")
    Page<Appointment> findByDniPatient(String dni,
                                       Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.cmp = ?1 AND a.is_deleted=false")
    Page<Appointment> findByCmpDoctor(String cmp,
                                      Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id_patient = ?1 AND a.is_deleted=false")
    Page<Appointment> findByIdPatient(UUID id,
                                      Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id_doctor = ?1 AND a.is_deleted=false")
    Page<Appointment> findByIdDoctor(UUID id,
                                     Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.is_deleted = false")
    Page<Appointment> findAllActive(Pageable pageable);

}
