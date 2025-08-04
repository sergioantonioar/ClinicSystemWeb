package org.example.clinic_system;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.*;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.enums.Gender;
import org.example.clinic_system.model.enums.MedicationFormat;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.service.Medicine.MedicineService;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.AdminProcesses;
import org.example.clinic_system.util.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class ClinicSystemApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private DoctorService doctorService;

    @Test
    void contextLoads() {

    }

    @Test
    void addAdmin() {
        RegisterAdminDTO registerAdminDTO = RegisterAdminDTO.builder()
                .first_name("John")
                .last_name("Smith")
                .dni("123456789")
                .email("Jhon@gmail.com")
                .address("123 Main St")
                .landline_phone("867423")
                .phone("354675453")
                .username("admin23243")
                .password("admin")
                .build();
        adminService.save(registerAdminDTO);
    }

    @Test
    void CrearMedicamentos() throws NotFoundException {

        //Ponen el id del admin que le genere
        UUID id_admin = UUID.fromString("678749f1-470f-4f9d-b94e-78e614d8f117");

        MedicineResponseDTO medicamento1 = new MedicineResponseDTO();
        medicamento1.setMedicine_name("Medicamento 1");
        medicamento1.setMedicine_description("Descripción del Medicamento 1");
        medicamento1.setMedicine_type(MedicationFormat.TABLETS.name());
        medicamento1.setMedicine_side_effect("Efecto Secundario 1");
        medicamento1.setMedicine_date(LocalDate.now().minusDays(1));
        medicineService.SaveMedicine(medicamento1, id_admin);

        MedicineResponseDTO medicamento2 = new MedicineResponseDTO();
        medicamento2.setMedicine_name("Medicamento 2");
        medicamento2.setMedicine_description("Descripción del Medicamento 2");
        medicamento2.setMedicine_type(MedicationFormat.CAPSULES.name());
        medicamento2.setMedicine_side_effect("Efecto Secundario 2");
        medicamento2.setMedicine_date(LocalDate.now().minusDays(2));
        medicineService.SaveMedicine(medicamento2, id_admin);

        MedicineResponseDTO medicamento3 = new MedicineResponseDTO();
        medicamento3.setMedicine_name("Medicamento 3");
        medicamento3.setMedicine_description("Descripción del Medicamento 3");
        medicamento3.setMedicine_type(MedicationFormat.SYRUPS.name());
        medicamento3.setMedicine_side_effect("Efecto Secundario 3");
        medicamento3.setMedicine_date(LocalDate.now().minusDays(3));
        medicineService.SaveMedicine(medicamento3, id_admin);

        MedicineResponseDTO medicamento4 = new MedicineResponseDTO();
        medicamento4.setMedicine_name("Medicamento 4");
        medicamento4.setMedicine_description("Descripción del Medicamento 4");
        medicamento4.setMedicine_type(MedicationFormat.SUSPENSIONS.name());
        medicamento4.setMedicine_side_effect("Efecto Secundario 4");
        medicamento4.setMedicine_date(LocalDate.now().minusDays(4));
        medicineService.SaveMedicine(medicamento4, id_admin);

        MedicineResponseDTO medicamento5 = new MedicineResponseDTO();
        medicamento5.setMedicine_name("Medicamento 5");
        medicamento5.setMedicine_description("Descripción del Medicamento 5");
        medicamento5.setMedicine_type(MedicationFormat.CREAMS.name());
        medicamento5.setMedicine_side_effect("Efecto Secundario 5");
        medicamento5.setMedicine_date(LocalDate.now().minusDays(5));
        medicineService.SaveMedicine(medicamento5, id_admin);

        MedicineResponseDTO medicamento6 = new MedicineResponseDTO();
        medicamento6.setMedicine_name("Medicamento 6");
        medicamento6.setMedicine_description("Descripción del Medicamento 6");
        medicamento6.setMedicine_type(MedicationFormat.OINTMENTS.name());
        medicamento6.setMedicine_side_effect("Efecto Secundario 6");
        medicamento6.setMedicine_date(LocalDate.now().minusDays(6));
        medicineService.SaveMedicine(medicamento6, id_admin);

        MedicineResponseDTO medicamento7 = new MedicineResponseDTO();
        medicamento7.setMedicine_name("Medicamento 7");
        medicamento7.setMedicine_description("Descripción del Medicamento 7");
        medicamento7.setMedicine_type(MedicationFormat.AEROSOLS.name());
        medicamento7.setMedicine_side_effect("Efecto Secundario 7");
        medicamento7.setMedicine_date(LocalDate.now().minusDays(7));
        medicineService.SaveMedicine(medicamento7, id_admin);

        MedicineResponseDTO medicamento8 = new MedicineResponseDTO();
        medicamento8.setMedicine_name("Medicamento 8");
        medicamento8.setMedicine_description("Descripción del Medicamento 8");
        medicamento8.setMedicine_type(MedicationFormat.INHALERS.name());
        medicamento8.setMedicine_side_effect("Efecto Secundario 8");
        medicamento8.setMedicine_date(LocalDate.now().minusDays(8));
        medicineService.SaveMedicine(medicamento8, id_admin);

        MedicineResponseDTO medicamento9 = new MedicineResponseDTO();
        medicamento9.setMedicine_name("Medicamento 9");
        medicamento9.setMedicine_description("Descripción del Medicamento 9");
        medicamento9.setMedicine_type(MedicationFormat.INJECTABLES.name());
        medicamento9.setMedicine_side_effect("Efecto Secundario 9");
        medicamento9.setMedicine_date(LocalDate.now().minusDays(9));
        medicineService.SaveMedicine(medicamento9, id_admin);

        MedicineResponseDTO medicamento10 = new MedicineResponseDTO();
        medicamento10.setMedicine_name("Medicamento 10");
        medicamento10.setMedicine_description("Descripción del Medicamento 10");
        medicamento10.setMedicine_type(MedicationFormat.TABLETS.name());
        medicamento10.setMedicine_side_effect("Efecto Secundario 10");
        medicamento10.setMedicine_date(LocalDate.now().minusDays(10));
        medicineService.SaveMedicine(medicamento10, id_admin);
    }

    @Test
    void crearPacientes() throws NotFoundException {
        UUID id_admin = UUID.fromString("678749f1-470f-4f9d-b94e-78e614d8f117"); // Usa el UUID correcto de tu admin

        PatientResponseDTO paciente1 = new PatientResponseDTO();
        paciente1.setFirst_name("Juan");
        paciente1.setLast_name("Pérez");
        paciente1.setEmail("juan1@example.com");
        paciente1.setAddress("Av. Lima 123");
        paciente1.setPhone("987654321");
        paciente1.setLandline_phone("012345678");
        paciente1.setDni("12345671");
        paciente1.setBirthdate(LocalDate.now().minusYears(25));
        paciente1.setGender(Gender.MALE);
        paciente1.setAge(25);
        paciente1.setAntecedent("Sin antecedentes relevantes");
        patientService.savePatient(id_admin, paciente1);

        PatientResponseDTO paciente2 = new PatientResponseDTO();
        paciente2.setFirst_name("María");
        paciente2.setLast_name("Gonzales");
        paciente2.setEmail("maria2@example.com");
        paciente2.setAddress("Av. Arequipa 456");
        paciente2.setPhone("987654322");
        paciente2.setLandline_phone("012345679");
        paciente2.setDni("12345672");
        paciente2.setBirthdate(LocalDate.now().minusYears(30));
        paciente2.setGender(Gender.FEMALE);
        paciente2.setAge(30);
        paciente2.setAntecedent("Alergia a penicilina");
        patientService.savePatient(id_admin, paciente2);

        PatientResponseDTO paciente3 = new PatientResponseDTO();
        paciente3.setFirst_name("Luis");
        paciente3.setLast_name("Ramírez");
        paciente3.setEmail("luis3@example.com");
        paciente3.setAddress("Calle 3 Sur");
        paciente3.setPhone("987654323");
        paciente3.setLandline_phone("012345680");
        paciente3.setDni("12345673");
        paciente3.setBirthdate(LocalDate.now().minusYears(40));
        paciente3.setGender(Gender.MALE);
        paciente3.setAge(40);
        paciente3.setAntecedent("Hipertensión");
        patientService.savePatient(id_admin, paciente3);

        PatientResponseDTO paciente4 = new PatientResponseDTO();
        paciente4.setFirst_name("Ana");
        paciente4.setLast_name("López");
        paciente4.setEmail("ana4@example.com");
        paciente4.setAddress("Jr. Amazonas 789");
        paciente4.setPhone("987654324");
        paciente4.setLandline_phone("012345681");
        paciente4.setDni("12345674");
        paciente4.setBirthdate(LocalDate.now().minusYears(22));
        paciente4.setGender(Gender.FEMALE);
        paciente4.setAge(22);
        paciente4.setAntecedent("Sin antecedentes");
        patientService.savePatient(id_admin, paciente4);

        PatientResponseDTO paciente5 = new PatientResponseDTO();
        paciente5.setFirst_name("Carlos");
        paciente5.setLast_name("Mendoza");
        paciente5.setEmail("carlos5@example.com");
        paciente5.setAddress("Av. Brasil 321");
        paciente5.setPhone("987654325");
        paciente5.setLandline_phone("012345682");
        paciente5.setDni("12345675");
        paciente5.setBirthdate(LocalDate.now().minusYears(28));
        paciente5.setGender(Gender.MALE);
        paciente5.setAge(28);
        paciente5.setAntecedent("Diabetes tipo 2");
        patientService.savePatient(id_admin, paciente5);

        PatientResponseDTO paciente6 = new PatientResponseDTO();
        paciente6.setFirst_name("Lucía");
        paciente6.setLast_name("Fernández");
        paciente6.setEmail("lucia6@example.com");
        paciente6.setAddress("Calle Ficticia 001");
        paciente6.setPhone("987654326");
        paciente6.setLandline_phone("012345683");
        paciente6.setDni("12345676");
        paciente6.setBirthdate(LocalDate.now().minusYears(35));
        paciente6.setGender(Gender.FEMALE);
        paciente6.setAge(35);
        paciente6.setAntecedent("Asma");
        patientService.savePatient(id_admin, paciente6);

        PatientResponseDTO paciente7 = new PatientResponseDTO();
        paciente7.setFirst_name("Eduardo");
        paciente7.setLast_name("Silva");
        paciente7.setEmail("eduardo7@example.com");
        paciente7.setAddress("Pasaje Real 100");
        paciente7.setPhone("987654327");
        paciente7.setLandline_phone("012345684");
        paciente7.setDni("12345677");
        paciente7.setBirthdate(LocalDate.now().minusYears(42));
        paciente7.setGender(Gender.MALE);
        paciente7.setAge(42);
        paciente7.setAntecedent("Colesterol alto");
        patientService.savePatient(id_admin, paciente7);

        PatientResponseDTO paciente8 = new PatientResponseDTO();
        paciente8.setFirst_name("Diana");
        paciente8.setLast_name("Reyes");
        paciente8.setEmail("diana8@example.com");
        paciente8.setAddress("Av. Primavera 654");
        paciente8.setPhone("987654328");
        paciente8.setLandline_phone("012345685");
        paciente8.setDni("12345678");
        paciente8.setBirthdate(LocalDate.now().minusYears(27));
        paciente8.setGender(Gender.FEMALE);
        paciente8.setAge(27);
        paciente8.setAntecedent("Migraña crónica");
        patientService.savePatient(id_admin, paciente8);

        PatientResponseDTO paciente9 = new PatientResponseDTO();
        paciente9.setFirst_name("Fernando");
        paciente9.setLast_name("Torres");
        paciente9.setEmail("fernando9@example.com");
        paciente9.setAddress("Jr. Ayacucho 900");
        paciente9.setPhone("987654329");
        paciente9.setLandline_phone("012345686");
        paciente9.setDni("12345679");
        paciente9.setBirthdate(LocalDate.now().minusYears(38));
        paciente9.setGender(Gender.MALE);
        paciente9.setAge(38);
        paciente9.setAntecedent("Cirugía reciente");
        patientService.savePatient(id_admin, paciente9);
    }

    @Test
    void crearEspecialidades() {
        SpecialtyResponseDTO especialidad1 = new SpecialtyResponseDTO();
        especialidad1.setName_specialty("Cardiología");
        Tuple<SpecialtyResponseDTO, UUID> resultado1 = specialtyService.saveSpecialty(especialidad1);
        System.out.println("Especialidad 1 creada con ID: " + resultado1.getSecond());

        SpecialtyResponseDTO especialidad2 = new SpecialtyResponseDTO();
        especialidad2.setName_specialty("Dermatología");
        Tuple<SpecialtyResponseDTO, UUID> resultado2 = specialtyService.saveSpecialty(especialidad2);
        System.out.println("Especialidad 2 creada con ID: " + resultado2.getSecond());

        SpecialtyResponseDTO especialidad3 = new SpecialtyResponseDTO();
        especialidad3.setName_specialty("Pediatría");
        Tuple<SpecialtyResponseDTO, UUID> resultado3 = specialtyService.saveSpecialty(especialidad3);
        System.out.println("Especialidad 3 creada con ID: " + resultado3.getSecond());
    }

    @Test
    void crearDoctores() throws NotFoundException {
        
        UUID adminId = UUID.fromString("678749f1-470f-4f9d-b94e-78e614d8f117"); // reemplaza por un UUID válido
        UUID specialistId1 = UUID.fromString("19063cd9-c293-462f-ab35-8269cea0fd80"); // Cardiología
        UUID specialistId2 = UUID.fromString("a7199ec0-62eb-41a0-9607-16a09c093574"); // Dermatología
        UUID specialistId3 = UUID.fromString("d0a00ea0-f709-41ed-90b4-b7cc75919f84"); // Pediatría

        RegisterDoctorDTO doctor1 = RegisterDoctorDTO.builder()
                .first_name("Carlos")
                .last_name("Gutiérrez")
                .email("carlos1@clinic.com")
                .address("Av. Salud 101")
                .phone("987654301")
                .landline_phone("012340001")
                .dni("45678901")
                .cmp("CMP12345")
                .username("carlos_doc1")
                .password("docpass1")
                .build();
        doctorService.SaveDoctorWithUsername(doctor1, adminId, specialistId1);

        RegisterDoctorDTO doctor2 = RegisterDoctorDTO.builder()
                .first_name("Lucía")
                .last_name("Reynoso")
                .email("lucia2@clinic.com")
                .address("Av. Salud 102")
                .phone("987654302")
                .landline_phone("012340002")
                .dni("45678902")
                .cmp("CMP12346")
                .username("lucia_doc2")
                .password("docpass2")
                .build();
        doctorService.SaveDoctorWithUsername(doctor2, adminId, specialistId2);

        RegisterDoctorDTO doctor3 = RegisterDoctorDTO.builder()
                .first_name("Javier")
                .last_name("Salinas")
                .email("javier3@clinic.com")
                .address("Av. Salud 103")
                .phone("987654303")
                .landline_phone("012340003")
                .dni("45678903")
                .cmp("CMP12347")
                .username("javier_doc3")
                .password("docpass3")
                .build();
        doctorService.SaveDoctorWithUsername(doctor3, adminId, specialistId3);

        RegisterDoctorDTO doctor4 = RegisterDoctorDTO.builder()
                .first_name("María")
                .last_name("Cáceres")
                .email("maria4@clinic.com")
                .address("Av. Salud 104")
                .phone("987654304")
                .landline_phone("012340004")
                .dni("45678904")
                .cmp("CMP12348")
                .username("maria_doc4")
                .password("docpass4")
                .build();
        doctorService.SaveDoctorWithUsername(doctor4, adminId, specialistId1); // Otra vez Cardiología
    }


}
