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
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ticket")
    private UUID id_ticket;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creation_date;

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
