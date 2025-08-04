package org.example.clinic_system.dto.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceDTO {
    private UUID id_service;
    private String name_Service;
    private Double price;
}
