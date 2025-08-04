package org.example.clinic_system.service.Medicine;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.MedicineDTO;
import org.example.clinic_system.dto.responseDTO.MedicineResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Medicine;
import org.example.clinic_system.repository.MedicineRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.util.MedicineProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImp implements MedicineService{

    private final MedicineRepository medicineRepository;
    private final AdminService adminService;

    @Value("20")
    private int size;

    @Override
    public Tuple<MedicineResponseDTO, UUID> SaveMedicine(MedicineResponseDTO medicineResponseDTO, UUID id_admin) throws NotFoundException {
        Admin admin = adminService.findById(id_admin);
        Medicine medicine = medicineRepository.save(MedicineProcesses.createMedicine(medicineResponseDTO, admin));
        return new Tuple<>(MedicineProcesses.createMedicineResponseDTO(medicine),medicine.getId_medicine());
    }

    @Override
    public MedicineDTO getMedicineDTOById(UUID id_medicine) throws NotFoundException {
        Medicine medicine = medicineRepository.findById(id_medicine).orElseThrow( () -> new NotFoundException("No se encontro la medicina con id: " + id_medicine));
        return MedicineProcesses.createMedicineDTO(medicine);
    }

    @Override
    public MedicineDTO getMedicineDTOByName(String medicine_name) throws NotFoundException {
        Medicine medicine = medicineRepository.findMedicineByMedicineName(medicine_name).orElseThrow(() -> new NotFoundException("No se encontro la medicina con el nombre: " + medicine_name));
        return MedicineProcesses.createMedicineDTO(medicine);
    }

    //RESERVA*** (NO USAR)
    @Override
    public Medicine getMedicineById(UUID id_medicine) throws NotFoundException {
        return medicineRepository.findMedicineById(id_medicine).orElseThrow(() -> new NotFoundException("No se encontro la medicina con id: " + id_medicine));
    }

    @Override
    public Medicine getMedicineByName(String medicine_name) throws NotFoundException {
        return medicineRepository.findMedicineByMedicineName(medicine_name).orElseThrow(()->new NotFoundException("No se encontro la medicina con el nombre: " + medicine_name));
    }

    @Override
    public List<MedicineDTO> getAllMedicines(int page){
        return MedicineProcesses.CreateMedicineResponseDTO(
                medicineRepository.findAllMedicines(
                        PageRequest.of(page, size)
                ).getContent()
        );
    }

    @Override
    public List<MedicineDTO> getAllMedicinesByType(String medicine_type, int page){
        return MedicineProcesses.CreateMedicineResponseDTO(
                medicineRepository.findAllMedicinesByType(
                        medicine_type,
                        PageRequest.of(page, size)
                ).getContent()
        );
    }

    @Override
    public List<MedicineDTO> getAllMedicinesByDate(LocalDate date, int page){
        return MedicineProcesses.CreateMedicineResponseDTO(
                medicineRepository.findAllMedicinesByDate(
                        date,
                        PageRequest.of(page, size)
                ).getContent()
        );
    }

    @Override
    public void updateMedicine(UUID id_medicine, MedicineResponseDTO medicineResponseDTO) throws NotFoundException {
        Medicine medicine = MedicineProcesses.updateMedicine(
                medicineRepository.findMedicineById(id_medicine).orElseThrow(
                        () -> new NotFoundException("No se encontro la medicina con el id: "+ id_medicine)),
                medicineResponseDTO
        );
        medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(UUID id_medicine) throws NotFoundException {
        Medicine medicine = medicineRepository.findMedicineById(id_medicine)
                .orElseThrow(()->new NotFoundException("No se encontro la medicina con id: " + id_medicine));
        medicine.setIs_deleted(true);
        medicineRepository.save(medicine);
    }
}
