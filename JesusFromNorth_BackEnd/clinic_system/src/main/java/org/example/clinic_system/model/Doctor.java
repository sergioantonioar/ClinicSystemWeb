package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_doctor")
    protected UUID id_doctor;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(
            name = "id_specialty",
            referencedColumnName = "id_specialty"
    )
    private Specialty specialty;

    @Column(nullable = false,unique = true)
    protected String cmp;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,CascadeType.REFRESH}
    )
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id_user"
    )
    protected User user;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,CascadeType.REFRESH}
    )
    @JoinColumn(
            name = "id_admin",
            referencedColumnName = "id_admin"
    )
    protected Admin admin;

    @Builder.Default
    private Boolean is_deleted=false;
}
