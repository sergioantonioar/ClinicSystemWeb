package org.example.clinic_system.repository;

import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    @Query("SELECT d FROM Doctor d WHERE d.id_doctor=?1 AND d.is_deleted=false")
    Optional<Doctor> findById(UUID id_doctor);

    @Query("SELECT d FROM Doctor d WHERE d.cmp=?1 AND d.is_deleted=false")
    Optional<Doctor> findByCmp(String cmp);

    @Query("SELECT d FROM Doctor d WHERE d.dni=?1 AND d.is_deleted=false")
    Optional<Doctor> findByDni(String dni);

    @Query("SELECT d FROM Doctor d WHERE d.user.id_user=?1 AND d.user.is_deleted=false")
    Optional<Doctor> findByUser(UUID id_user);

    @Query("SELECT d FROM Doctor d WHERE d.is_deleted=false ")
    Page<Doctor> findAll(Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.specialty.id_specialty=?1 AND d.is_deleted=false ")
    Page<Doctor> findAllBySpecialty(UUID id_specialty,Pageable pageable);
}
