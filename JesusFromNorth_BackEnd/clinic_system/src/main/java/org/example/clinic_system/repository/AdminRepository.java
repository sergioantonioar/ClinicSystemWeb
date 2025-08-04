package org.example.clinic_system.repository;

import org.example.clinic_system.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Optional<Admin> findByDni(String dni);

    @Query("SELECT a FROM Admin a WHERE a.user.id_user=?1")
    Optional<Admin> findByUser(UUID id_user);

}
