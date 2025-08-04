package org.example.clinic_system.dto.entityDTO;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Specialty;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DoctorDTO extends PersonDTO {
    private UUID id_doctor;
    private Specialty specialty;
    private String cmp;
}
