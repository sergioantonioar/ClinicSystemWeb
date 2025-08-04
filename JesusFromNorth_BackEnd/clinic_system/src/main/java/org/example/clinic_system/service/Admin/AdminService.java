package org.example.clinic_system.service.Admin;

import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;

import java.util.UUID;

public interface AdminService {
    void save(RegisterAdminDTO registerAdminDTO);
    Admin findByDni(String dni) throws NotFoundException;
    Admin findById(UUID id_admin) throws NotFoundException;
    Admin findByUser(UUID id_user) throws NotFoundException;
}
