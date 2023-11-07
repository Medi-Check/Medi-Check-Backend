package com.example.medicheckbackend.service;

import static com.example.medicheckbackend.global.DataHub.DataHub.edgeAgent;

import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.global.DataHub.DataHub;
import com.example.medicheckbackend.repository.EatMedicineRepository;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wisepaas.datahub.java.sdk.model.edge.EdgeConfig;
import wisepaas.datahub.java.sdk.model.edge.EdgeData;
import wisepaas.datahub.java.sdk.model.edge.EdgeData.Tag;

@RequiredArgsConstructor
@Service
public class EatMedicineService {

    private final EatMedicineRepository eatMedicineRepository;
    private final TakeMedicineRepository takeMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;




    public String checkMedicine(EatMedicineInfo eatMedicineInfo) {
        EdgeData data = new EdgeData();

        EdgeData.Tag MedicineCheck = new Tag(); {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "MedicineCheck";
            MedicineCheck.Value = eatMedicineInfo.getChecked();
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        Member member = memberRepository.findMemberByNickName(eatMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(eatMedicineInfo.getMedicineName());

        TakeMedicine takMedicine = takeMedicineRepository.findByWeekAndMedicineAndMemberAndTime(
                eatMedicineInfo.getWeek(), medicine, member, eatMedicineInfo.getTime());

        EatMedicine eatMedicine = new EatMedicine(takMedicine, eatMedicineInfo.getChecked());
        eatMedicineRepository.save(eatMedicine);
        return "약 체크 완료";
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendSuccess() {
        EdgeData data = new EdgeData();
        Integer success = eatMedicineRepository.countByCheckedIsSuccess();
        EdgeData.Tag MedicineCheck = new Tag(); {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "Success";
            MedicineCheck.Value = success;
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendFail() {
        EdgeData data = new EdgeData();
        Integer fail = eatMedicineRepository.countByCheckedIsFail();
        EdgeData.Tag MedicineCheck = new Tag(); {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "Fail";
            MedicineCheck.Value = fail;
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }

}
