package org.example.clinic_system.repository;

import org.example.clinic_system.model.ServiceSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceSpecialty, UUID> {

    @Query("SELECT s FROM ServiceSpecialty s WHERE s.specialty.id_specialty=?1 AND s.specialty.is_deleted=false")
    List<ServiceSpecialty> findBySpecialtyId(UUID id_specialty);

}
