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
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bill")
    private UUID id_bill;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creation_date;

    private String ruc;

    @OneToOne(
            cascade ={CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "id_appointment",
            referencedColumnName = "id_appointment"
    )
    private Appointment appointment;

    private Double total_amount;
}
