package com.example.medicheckbackend.service;


import static com.example.medicheckbackend.global.DataHub.DataHub.edgeAgent;

import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.repository.MedicineRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wisepaas.datahub.java.sdk.model.edge.EdgeData;
import wisepaas.datahub.java.sdk.model.edge.EdgeData.Tag;

@RequiredArgsConstructor
@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public String insertMedicine(MedicineInfo medicineInfo) {
        Medicine medicine = new Medicine(medicineInfo.getName(), medicineInfo.getMakeDate(), medicineInfo.getExpirationDate(),
                medicineInfo.getAmount(), medicineInfo.getInformation(), medicineInfo.getMedicineContainer(),
                medicineInfo.getMedicineCost() );

        medicineRepository.save(medicine);
        return "약 정보 저장 완료";
    }




}
