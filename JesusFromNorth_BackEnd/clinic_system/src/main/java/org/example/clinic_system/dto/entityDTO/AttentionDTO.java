package org.example.clinic_system.dto.entityDTO;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.AttentionType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttentionDTO {

    private UUID id_attention;

    private String diagnosis;

    private String treatment;

    private AttentionType attentionType;

}
