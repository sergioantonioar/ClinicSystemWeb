package org.example.clinic_system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prescription")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prescription")
    private UUID id_prescription;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_attention",
            referencedColumnName = "id_attention"
    )
    private Attention attention;

    @JsonFormat(pattern = "yyyy-MM-dd") //use example : 2025-04-16
    @Column(nullable = false)
    private LocalDate date;

    @Builder.Default
    private Boolean is_deleted=false;
}
