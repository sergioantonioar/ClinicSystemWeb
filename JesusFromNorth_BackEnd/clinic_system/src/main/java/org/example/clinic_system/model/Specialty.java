package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "specialty")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_specialty")
    private UUID id_specialty;

    private String specialty_name;

    @Builder.Default
    private Boolean is_deleted=false;

}
