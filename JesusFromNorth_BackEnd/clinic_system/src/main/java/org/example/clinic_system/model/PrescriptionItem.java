package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.MedicationFormat;

import java.util.UUID;

@Entity
@Table(name = "prescription_item" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrescriptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prescription_item")
    private UUID id_prescription_item;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_medicine",
            referencedColumnName = "id_medicine",
            nullable = false
    )
    private Medicine medicine;

    @Column(nullable = false)
    private Float dose;

    @Column(nullable = false)
    private Float frequency;

    @Column(nullable = false)
    private Byte duration;

    private MedicationFormat medicationFormat;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_prescription",
            referencedColumnName = "id_prescription",
            nullable = false
    )
    private Prescription prescription;
}
