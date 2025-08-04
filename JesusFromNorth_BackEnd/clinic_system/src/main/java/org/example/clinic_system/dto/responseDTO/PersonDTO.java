package org.example.clinic_system.dto.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PersonDTO {
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone;
    private String landline_phone;
    private String dni;
}
