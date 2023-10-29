package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public String insertMedicine(MedicineInfo medicineInfo) {
        Medicine medicine = new Medicine(medicineInfo.getName(), medicineInfo.getMakeDate(), medicineInfo.getAmount(),
                medicineInfo.getInformation());
        medicineRepository.save(medicine);
        return "약 정보 저장 완료";
    }
}
