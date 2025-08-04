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
@Table(name = "medicine")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_medicine")
    private UUID id_medicine;

    @Column(nullable = false)
    private String medicine_name;

    @Column(nullable = true)
    private String medicine_description;

    @Column(nullable = true)
    private String medicine_type;

    @Column(nullable = true)
    private String medicine_side_effect;

    @JsonFormat(pattern = "yyyy-MM-dd") //use example : 2025-04-16
    @Column(nullable = false)
    private LocalDate medicine_date;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinColumn(
            name = "id_admin",
            referencedColumnName = "id_admin"
    )
    protected Admin admin;

    @Builder.Default
    private Boolean is_deleted=false;
}
