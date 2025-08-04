package org.example.clinic_system.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PatientResponseDTO extends PersonDTO{
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private Gender gender;
    private Integer age;
    private String antecedent;
}
