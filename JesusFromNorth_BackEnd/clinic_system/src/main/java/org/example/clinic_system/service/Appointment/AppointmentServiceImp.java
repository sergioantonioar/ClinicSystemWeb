package org.example.clinic_system.service.Appointment;


import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.repository.AppointmentRepository;

import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.service.ServiceAux.SpecialtyWithService;
import org.example.clinic_system.service.ServiceSpecialty.ServiceService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.AppointmentProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final AdminService adminService;

    private final DoctorService doctorService;

    private final PatientService patientService;

    private final ServiceService serviceService;

    @Value("${appointment-size}")
    private int size;

    @Value("${appointment-doctor-size}")
    private int sizeDoctor;

    @Value("${appointment-patient-size}")
    private int sizePatient;

    @Override
    public Tuple<AppointmentResponseDTO, UUID> saveAppointment(
            UUID id_admin, UUID id_doctor, String dni_patient, AppointmentResponseDTO responseDTO
    ) throws NotFoundException {
        Admin admin = adminService.findById(id_admin);
        Doctor doctor = doctorService.getDoctorById(id_doctor);
        Patient patient = patientService.getPatientByDni(dni_patient);
        Appointment appointment = appointmentRepository.save(AppointmentProcesses.CreateAppointment(responseDTO,doctor,patient,admin));
        return new Tuple<>(
                AppointmentProcesses.CreateAppointmentResponseDTO(appointment),
                appointment.getId_appointment()
        );
    }

    @Override
    public void updateAppointment(UUID id_appointment, AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException {
        Appointment appointment = AppointmentProcesses.UpdateAppointment(
                appointmentRepository.findByIdAppointment(id_appointment)
                        .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment) ),
                appointmentResponseDTO
        );
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO getAppointmentDTOById(UUID id_appointment) throws NotFoundException {
        return AppointmentProcesses.CreateAppointmentDTO(
                appointmentRepository.findByIdAppointment(id_appointment)
                        .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment))
        );
    }

    @Override
    public Appointment getAppointmentById(UUID id_appointment) throws NotFoundException {
        return appointmentRepository.findByIdAppointment(id_appointment)
                .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment));
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByIdPatient(UUID id_patient,int page) throws NotFoundException {
        return appointmentRepository.findByIdPatient(
                id_patient,
                PageRequest.of(page,sizePatient)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByIdDoctor(UUID id_doctor,int page) throws NotFoundException {
        return appointmentRepository.findByIdDoctor(
                id_doctor,
                PageRequest.of(page,sizeDoctor)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByCmpDoctor(String cmp,int page) throws NotFoundException {
        return appointmentRepository.findByCmpDoctor(
                cmp,
                PageRequest.of(page,sizeDoctor)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDniPatient(String dni,int page) throws NotFoundException {
        return appointmentRepository.findByDniPatient(
                dni,
                PageRequest.of(page,sizePatient)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(int page) {
        return appointmentRepository.findAllActive(
                PageRequest.of(page,size)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public void deleteAppointment(UUID id_appointment) throws NotFoundException {
        Appointment appointment = appointmentRepository.findByIdAppointment(id_appointment)
                .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment));
        appointment.setIs_deleted(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public byte[] generateInvoice(UUID id_appointment) throws NotFoundException, JRException {
        //obtener cita
        AppointmentDTO appointmentDTO = getAppointmentDTOById(id_appointment);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //parametros para el reporte
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("patientFirstName", appointmentDTO.getPatient().getFirst_name());
        parameters.put("patientLastName", appointmentDTO.getPatient().getLast_name());
        parameters.put("patientDni", appointmentDTO.getPatient().getDni());
        parameters.put("doctorFirstName", appointmentDTO.getDoctor().getFirst_name());
        parameters.put("doctorLastName", appointmentDTO.getDoctor().getLast_name());
        parameters.put("appointmentDate", appointmentDTO.getDate_appointment().format(formatter));
        parameters.put("appointmentDateAttention", appointmentDTO.getDate_attention().format(formatter));

        //traer los servicios por especialidad
        List<ServiceDTO> serviceDTOS = serviceService.getAllServicesBySpecialty(appointmentDTO.getDoctor().getSpecialty().getId_specialty());

        //agregar preciobase, igv e importetotal por servicio
        List<Map<String, Object>> servicesWithIGV = new ArrayList<>();
        for (ServiceDTO service : serviceDTOS) {
            double precioBase = service.getPrice();
            double igv = Math.round(precioBase * 0.18 * 100) / 100.0;
            double importeTotal = Math.round((precioBase + igv) * 100) / 100.0;

            Map<String, Object> item = new HashMap<>();
            item.put("name", service.getName_Service());
            item.put("precioBase", precioBase);
            item.put("igv", igv);
            item.put("importeTotal", importeTotal);

            servicesWithIGV.add(item);
        }

        //calculo general de totales
        double totalBase = serviceDTOS.stream().mapToDouble(ServiceDTO::getPrice).sum();
        double totalIGV = Math.round(totalBase * 0.18 * 100) / 100.0;
        double totalFinal = Math.round((totalBase + totalIGV) * 100) / 100.0;

        parameters.put("totalBase", totalBase);
        parameters.put("totalIGV", totalIGV);
        parameters.put("totalFinal", totalFinal);

        //logo
        InputStream logoStream = getClass().getResourceAsStream("/LogoCJDNBoleta.png");
        parameters.put("logo", logoStream);

        //qr generator
        InputStream qrStream = getClass().getResourceAsStream("/qrboleta.png");
        parameters.put("qr", qrStream);

        //subreporte
        JRBeanCollectionDataSource serviciosDataSource = new JRBeanCollectionDataSource(servicesWithIGV);
        parameters.put("SERVICIOS_DS", serviciosDataSource);

        //cargar reporte
        InputStream jasperStream = this.getClass().getResourceAsStream("/receipts/boleta.jasper"); //agregar archivo
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        //llenar reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());

        //exportar a PDF como byte[]
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
