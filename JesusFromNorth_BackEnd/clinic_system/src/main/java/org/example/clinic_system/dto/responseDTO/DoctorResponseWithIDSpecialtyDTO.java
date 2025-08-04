package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.Specialty;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DoctorResponseWithIDSpecialtyDTO extends PersonDTO {
    private UUID id_specialty;
    private String cmp;
}
