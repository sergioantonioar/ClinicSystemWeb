package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_service")
    private UUID id_service;

    private String service_name;

    private Double price;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_specialty",
            referencedColumnName = "id_specialty"
    )
    private Specialty specialty;

}
