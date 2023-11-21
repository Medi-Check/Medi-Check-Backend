package com.example.medicheckbackend.service;

import static com.example.medicheckbackend.global.DataHub.DataHub.edgeAgent;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.HealthRateInfo;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineResponseDto.EatMedicineRes;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.repository.EatMedicineRepository;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wisepaas.datahub.java.sdk.model.edge.EdgeData;
import wisepaas.datahub.java.sdk.model.edge.EdgeData.Tag;

@RequiredArgsConstructor
@Service
public class EatMedicineService {

    private final EatMedicineRepository eatMedicineRepository;
    private final TakeMedicineRepository takeMedicineRepository;
    private final LEDService ledService;


    @Transactional
    public Long checkMedicine(EatMedicineInfo eatMedicineInfo) throws IOException {

        TakeMedicine takeMedicine = takeMedicineRepository.findByIdWithMedicine(eatMedicineInfo.getTakeMedicineId());

        EdgeData data = new EdgeData();
        EdgeData.Tag MedicineCheck = new Tag();
        {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "MedicineCheck";
            MedicineCheck.Value = eatMedicineInfo.getChecked();
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);


        takeMedicine.getMedicine().modifyAmount(takeMedicine.getAmounts());

        // 약 먹었는지 체크
        EatMedicine eatMedicine = new EatMedicine(takeMedicine, eatMedicineInfo.getChecked());
        eatMedicineRepository.save(eatMedicine);

        ledService.OffLED(takeMedicine.getMedicine().getMedicineContainer());
        return eatMedicine.getId();
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendSuccess() {
        EdgeData data = new EdgeData();
        Integer success = eatMedicineRepository.countByCheckedIsSuccess();
        EdgeData.Tag MedicineCheck = new Tag();
        {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "Success";
            MedicineCheck.Value = 30;
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
        EdgeData.Tag MedicineCheck = new Tag();
        {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "Fail";
            MedicineCheck.Value = 5;
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }

    @Transactional
    public String healthRate(HealthRateInfo healthRateInfo) {
        EatMedicine eatMedicine = eatMedicineRepository.findByIdWithTakeMedicine(healthRateInfo.getEatMedicineId());
        eatMedicine.setHealthRate(healthRateInfo.getHealthRate());

        EdgeData data = new EdgeData();
        EdgeData.Tag HealthRate = new Tag();
        {
            HealthRate.DeviceId = "MediCheck";
            HealthRate.TagName = "healthRate" + eatMedicine.getTakeMedicine().getMedicine().getMedicineContainer();
            HealthRate.Value = healthRateInfo.getHealthRate();
        }

        data.TagList.add(HealthRate);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
        return "건강 체크 완료";
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public String sendSuccessAmount() {
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("Y-M-d"));

        //오늘 먹은 약 조회
        List<EatMedicine> eatMedicine = eatMedicineRepository.findAllByCreatedAt(Weekend.valueOf(dayOfWeek.toString()),
                now);

        //오늘 먹어야 할 약 조회
        List<TakeMedicine> takeMedicine = takeMedicineRepository.findAllByCreatedAt(
                Weekend.valueOf(dayOfWeek.toString()), now);

        int success = 0;
        int sum = 0;

        // 오늘 얼마나 먹었는지
        for (EatMedicine medicine : eatMedicine) {
            success += medicine.getTakeMedicine().getAmounts() * medicine.getTakeMedicine().getMedicine()
                    .getMedicineCost();
        }

        // 원래 먹어야 하는 양
        for (TakeMedicine medicine : takeMedicine) {
            sum += medicine.getMedicine().getMedicineCost() * medicine.getAmounts();
        }


        EdgeData data = new EdgeData();


        EdgeData.Tag SuccessCost = new Tag();
        {
            SuccessCost.DeviceId = "MediCheck";
            SuccessCost.TagName = "SuccessCost";
            SuccessCost.Value = 4000;
        }
        data.TagList.add(SuccessCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag FailCost = new Tag();
        {
            FailCost.DeviceId = "MediCheck";
            FailCost.TagName = "FailCost";
            FailCost.Value = 6000;
        }
        data.TagList.add(FailCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag TotalCost = new Tag();
        {
            TotalCost.DeviceId = "MediCheck";
            TotalCost.TagName = "TotalCost";
            TotalCost.Value = 10000;
        }
        data.TagList.add(TotalCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        return "데이터 전송 완료";
    }

    public List<EatMedicineRes> selectAllEatMedicine() {
        List<EatMedicine> eatMedicines = eatMedicineRepository.findAll();
        List<EatMedicineRes> eatMedicineRes = new ArrayList<>();

        for (int i = 0; i < eatMedicines.size(); i++) {
            eatMedicineRes.add(new EatMedicineRes(takeMedicineRepository.findByIdWithMedicine(eatMedicines.get(i).getTakeMedicine().getId())
                    ,eatMedicines.get(i)));
        }

        return eatMedicineRes;
    }

    @Transactional
    public String deleteEatMedicineById(Long medicineId) {
        eatMedicineRepository.findById(medicineId);
        return "약 정보 삭제 완료";
    }
}
