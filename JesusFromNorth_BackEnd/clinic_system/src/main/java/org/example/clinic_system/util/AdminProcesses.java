package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.AdminDTO;
import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;

public class AdminProcesses {
    public static Admin CreateAdmin(RegisterAdminDTO registerAdminDTO,User newUser) {
        return Admin.builder()
                .first_name(registerAdminDTO.getFirst_name())
                .last_name(registerAdminDTO.getLast_name())
                .dni(registerAdminDTO.getDni())
                .email(registerAdminDTO.getEmail())
                .address(registerAdminDTO.getAddress())
                .phone(registerAdminDTO.getPhone())
                .landline_phone(registerAdminDTO.getLandline_phone())
                .user(newUser)
                .build();
    }
    public static AdminDTO CreateAdminDTO(Admin admin) {
        return AdminDTO.builder()
                .id_admin(admin.getId_admin())
                .first_name(admin.getFirst_name())
                .last_name(admin.getLast_name())
                .email(admin.getEmail())
                .address(admin.getAddress())
                .phone(admin.getPhone())
                .landline_phone(admin.getLandline_phone())
                .dni(admin.getDni())
                .build();
    }
}
