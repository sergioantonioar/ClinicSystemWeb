package org.example.clinic_system.repository;

import org.example.clinic_system.model.Attention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AttentionRepository extends JpaRepository<Attention, UUID> {

    @Query("SELECT a FROM Attention a where a.is_deleted=false and a.appointment.patient.is_deleted=false and a.appointment.doctor.is_deleted=false")
    Page<Attention> findAll(Pageable pageable);

    @Query("SELECT a FROM Attention a where a.is_deleted=false and a.appointment.patient.id_patient=?1 and a.appointment.patient.is_deleted=false")
    Page<Attention> findAllByIdPatient(UUID id_patient,Pageable pageable);

    @Query("SELECT a FROM Attention a where a.is_deleted=false and a.appointment.doctor.id_doctor=?1 and a.appointment.doctor.is_deleted=false ")
    Page<Attention> findAllByIdDoctor(UUID id_doctor,Pageable pageable);

    @Query("SELECT a FROM Attention a where a.is_deleted=false and a.appointment.patient.dni=?1 and a.appointment.patient.is_deleted=false ")
    Page<Attention> findAllByDniPatient(String dni_patient,Pageable pageable);

    @Query("SELECT a FROM Attention a where a.is_deleted=false and a.appointment.doctor.cmp=?1 and a.appointment.doctor.is_deleted=false  ")
    Page<Attention> findAllByCmpDoctor(String cmp_doctor,Pageable pageable);
}
