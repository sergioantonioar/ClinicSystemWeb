package org.example.clinic_system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient")
    protected UUID id_patient;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false)
    protected Integer age;

    @Column(nullable = true)
    protected String antecedent;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_admin",
            referencedColumnName = "id_admin"
    )
    private Admin admin;

    @Builder.Default
    private Boolean is_deleted=false;
}
