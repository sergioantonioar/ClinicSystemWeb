package org.example.clinic_system.dto.responseDTO;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.Specialty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DoctorResponseDTO extends PersonDTO{
    private Specialty specialty;
    private String cmp;
}
