package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterAdminDTO {
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone;
    private String landline_phone;
    private String dni;
    private String username;
    private String password;
}
