package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.MedicineDTO;
import org.example.clinic_system.dto.responseDTO.MedicineResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Medicine;

import java.util.List;

public class MedicineProcesses {

    public static Medicine createMedicine(MedicineResponseDTO medicineResponseDTO, Admin admin) {
        return Medicine.builder()
                .medicine_name(medicineResponseDTO.getMedicine_name())
                .medicine_description(medicineResponseDTO.getMedicine_description())
                .medicine_type(medicineResponseDTO.getMedicine_type())
                .medicine_date(medicineResponseDTO.getMedicine_date())
                .medicine_side_effect(medicineResponseDTO.getMedicine_side_effect())
                .admin(admin)
                .build();
    }

    public static MedicineResponseDTO createMedicineResponseDTO(Medicine medicine) {
        return MedicineResponseDTO.builder()
                .medicine_name(medicine.getMedicine_name())
                .medicine_description(medicine.getMedicine_description())
                .medicine_type(medicine.getMedicine_type())
                .medicine_date(medicine.getMedicine_date())
                .medicine_side_effect(medicine.getMedicine_side_effect())
                .build();
    }

    public static MedicineDTO createMedicineDTO(Medicine medicine) {
        return MedicineDTO.builder()
                .id_medicine(medicine.getId_medicine())
                .medicine_name(medicine.getMedicine_name())
                .medicine_description(medicine.getMedicine_description())
                .medicine_type(medicine.getMedicine_type())
                .medicine_date(medicine.getMedicine_date())
                .medicine_side_effect(medicine.getMedicine_side_effect())
                .build();
    }

    public static Medicine updateMedicine(Medicine medicine, MedicineResponseDTO medicineResponseDTO){
        if(medicineResponseDTO.getMedicine_name() != null) {
            medicine.setMedicine_name(medicineResponseDTO.getMedicine_name());
        }
        if(medicineResponseDTO.getMedicine_description() != null) {
            medicine.setMedicine_description(medicineResponseDTO.getMedicine_description());
        }
        if(medicineResponseDTO.getMedicine_type() != null) {
            medicine.setMedicine_type(medicineResponseDTO.getMedicine_type());
        }
        if(medicineResponseDTO.getMedicine_date() != null) {
            medicine.setMedicine_date(medicineResponseDTO.getMedicine_date());
        }
        if(medicineResponseDTO.getMedicine_side_effect() != null) {
            medicine.setMedicine_side_effect(medicineResponseDTO.getMedicine_side_effect());
        }
        return medicine;
    }

    public static List<MedicineDTO> CreateMedicineResponseDTO(List<Medicine>medicineList) {
        return medicineList.stream()
                .map(MedicineProcesses::createMedicineDTO)
                .toList();
    }

}
