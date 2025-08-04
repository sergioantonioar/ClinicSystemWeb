package org.example.clinic_system.dto.entityDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PatientDTO extends PersonDTO {
    private UUID id_patient;
    private LocalDate birthdate;
    private Gender gender;
    private Integer age;
    private String antecedent;
}
