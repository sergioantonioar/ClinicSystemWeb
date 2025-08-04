package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.UserDTO;
import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;

public class UserProcesses {

    public static UserDTO CreateuserDTO(User user){
        return UserDTO.builder()
                .id_user(user.getId_user())
                .role(user.getRole())
                .is_deleted(user.getIs_deleted())
                .build();
    }

    public static User CreateUserAdmin(RegisterAdminDTO registerAdminDTO){
        return User.builder()
                .username(registerAdminDTO.getUsername())
                .password(registerAdminDTO.getPassword())
                .role(Rol.ADMIN)
                .build();
    }

}
