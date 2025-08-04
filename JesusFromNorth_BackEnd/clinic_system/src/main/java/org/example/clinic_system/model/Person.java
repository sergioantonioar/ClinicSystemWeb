package org.example.clinic_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String phone;
    @Column(nullable = true)
    private String landline_phone;
    @Column(nullable = false,unique = true)
    private String dni;

}
