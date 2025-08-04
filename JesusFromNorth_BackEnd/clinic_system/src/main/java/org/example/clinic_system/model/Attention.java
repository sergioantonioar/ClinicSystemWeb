package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clinic_system.model.enums.AttentionType;

import java.util.UUID;


@Entity
@Table(name = "attention")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Attention {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_attention")
    private UUID id_attention;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_appointment",
            referencedColumnName = "id_appointment"
    )
    private Appointment appointment;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String treatment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttentionType attentionType;

    @Builder.Default
    private Boolean is_deleted=false;

}
