package org.example.clinic_system.dto.responseDTO;


import org.example.clinic_system.model.enums.Rol;

import java.util.UUID;

public class UserResponseDTO {

    private UUID id_user;

    private Rol role;

    private Boolean is_deleted=false;

}
