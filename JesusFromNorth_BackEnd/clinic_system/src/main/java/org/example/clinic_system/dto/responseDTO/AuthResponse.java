package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.Rol;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthResponse<T> {
    private String token;
    private Rol role;
    private T data;
}
