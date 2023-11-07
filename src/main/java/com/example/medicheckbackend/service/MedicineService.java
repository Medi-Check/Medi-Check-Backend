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

    public String insertMedicine(MedicineInfo medicineInfo, int medicineContainer) {
        Medicine medicine = new Medicine(medicineInfo.getName(), medicineInfo.getMakeDate(), medicineInfo.getAmount(),
                medicineInfo.getInformation(), medicineContainer);

        EdgeData data = new EdgeData();
        String tagName = getMedicineContainer(medicineContainer);
        String datas[] = new String[5];
        datas[0] = medicine.getName();
        datas[1] = medicine.getMakeDate();
        datas[2] = String.valueOf(medicine.getAmount());
        datas[3] = medicine.getInformation();
        datas[4] = String.valueOf(medicine.getMedicineContainer());

        EdgeData.Tag MedicineInformation = new Tag(); {
            MedicineInformation.DeviceId = "MediCheck";
            MedicineInformation.TagName = tagName;
            MedicineInformation.Value = datas;
        }
        data.TagList.add(MedicineInformation);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        medicineRepository.save(medicine);
        return "약 정보 저장 완료";
    }

    public String getMedicineContainer (int medicineContainer) {
        if(medicineContainer == 1) return "first";
        if(medicineContainer == 2) return "second";
        if(medicineContainer == 3) return "third";
        if(medicineContainer == 4) return  "fourth";
        return "0";
    }


}
