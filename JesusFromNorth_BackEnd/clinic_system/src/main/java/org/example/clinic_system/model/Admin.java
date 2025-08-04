package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Entity
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_admin")
    private UUID id_admin;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH} // ✅ NO uses ALL
    )
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id_user"
    )
    private User user;
}
