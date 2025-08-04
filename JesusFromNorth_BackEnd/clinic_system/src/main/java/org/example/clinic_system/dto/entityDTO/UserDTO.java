package org.example.clinic_system.dto.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.Rol;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private UUID id_user;
    private Rol role;
    private Boolean is_deleted=false;
}
