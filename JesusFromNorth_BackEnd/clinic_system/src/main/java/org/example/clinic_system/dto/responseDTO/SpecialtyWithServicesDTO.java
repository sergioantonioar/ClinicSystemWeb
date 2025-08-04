package org.example.clinic_system.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpecialtyWithServicesDTO {
    private UUID id_specialty;
    private String name_specialty;
    private List<ServiceDTO> ListServicesDTO;
}
