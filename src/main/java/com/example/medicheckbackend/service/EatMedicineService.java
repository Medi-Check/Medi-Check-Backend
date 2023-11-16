package com.example.medicheckbackend.service;

import static com.example.medicheckbackend.global.DataHub.DataHub.edgeAgent;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.HealthRateInfo;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.repository.EatMedicineRepository;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long checkMedicine(EatMedicineInfo eatMedicineInfo) {
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

        Member member = memberRepository.findMemberByNickName(eatMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(eatMedicineInfo.getMedicineName());

        TakeMedicine takMedicine = takeMedicineRepository.findByWeekAndMedicineAndMemberAndHourAndMinute(
                eatMedicineInfo.getWeek(), medicine, member, eatMedicineInfo.getHour(), eatMedicineInfo.getMinute());

        medicine.modifyAmount(takMedicine.getAmounts());

        // 약 먹었는지 체크
        EatMedicine eatMedicine = new EatMedicine(takMedicine, eatMedicineInfo.getChecked());
        eatMedicineRepository.save(eatMedicine);
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
        EdgeData.Tag MedicineCheck = new Tag();
        {
            MedicineCheck.DeviceId = "MediCheck";
            MedicineCheck.TagName = "Fail";
            MedicineCheck.Value = fail;
        }
        data.TagList.add(MedicineCheck);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);
    }

    @Transactional
    public String healthRate(HealthRateInfo healthRateInfo) {
        EatMedicine eatMedicine = eatMedicineRepository.findById(healthRateInfo.getEatMedicineId()).orElseThrow();
        eatMedicine.setHealthRate(healthRateInfo.getHealthRate());

        EdgeData data = new EdgeData();
        EdgeData.Tag HealthRate = new Tag();
        {
            HealthRate.DeviceId = "MediCheck";
            HealthRate.TagName = "healthRate";
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
        List<EatMedicine> eatMedicine = eatMedicineRepository.findAllByCreatedAt(Weekend.valueOf(dayOfWeek.toString()), now);

        //오늘 먹어야 할 약 조회
        List<TakeMedicine> takeMedicine = takeMedicineRepository.findAllByCreatedAt(Weekend.valueOf(dayOfWeek.toString()), now);

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
        System.out.println(success + " " + sum);
        EdgeData data = new EdgeData();

        EdgeData.Tag SuccessCost = new Tag();
        {
            SuccessCost.DeviceId = "MediCheck";
            SuccessCost.TagName = "SuccessCost";
            SuccessCost.Value = success;
        }
        data.TagList.add(SuccessCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        EdgeData.Tag FailCost = new Tag();
        {
            FailCost.DeviceId = "MediCheck";
            FailCost.TagName = "FailCost";
            FailCost.Value = sum - success;
        }
        data.TagList.add(FailCost);
        data.Timestamp = new Date();
        edgeAgent.SendData(data);

        return "데이터 전송 완료";
    }
}
