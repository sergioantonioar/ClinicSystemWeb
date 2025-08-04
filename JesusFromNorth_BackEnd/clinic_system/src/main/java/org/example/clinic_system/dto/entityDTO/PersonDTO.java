package org.example.clinic_system.dto.entityDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PersonDTO {

    protected String first_name;
    protected String last_name;
    protected String email;
    protected String address;
    protected String phone;
    protected String landline_phone;
    protected String dni;

}
