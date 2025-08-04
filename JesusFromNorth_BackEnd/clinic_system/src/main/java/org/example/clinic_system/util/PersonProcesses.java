package org.example.clinic_system.util;

import org.example.clinic_system.dto.responseDTO.PersonDTO;
import org.example.clinic_system.model.Person;

import java.util.ArrayList;

public class PersonProcesses {
    public static void UpdatePerson(Person person, PersonDTO personDTO) {
        if (personDTO.getFirst_name() != null) {
            person.setFirst_name(personDTO.getFirst_name());
        }
        if (personDTO.getLast_name() != null) {
            person.setLast_name(personDTO.getLast_name());
        }
        if (personDTO.getEmail() != null) {
            person.setEmail(personDTO.getEmail());
        }
        if (personDTO.getAddress() != null) {
            person.setAddress(personDTO.getAddress());
        }
        if (personDTO.getPhone() != null) {
            person.setPhone(personDTO.getPhone());
        }
        if (personDTO.getLandline_phone() != null) {
            person.setLandline_phone(personDTO.getLandline_phone());
        }
        if (personDTO.getDni() != null) {
            person.setDni(personDTO.getDni());
        }
    }
}
