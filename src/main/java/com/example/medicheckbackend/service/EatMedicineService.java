package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.repository.EatMedicineRepository;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EatMedicineService {

    private final EatMedicineRepository eatMedicineRepository;
    private final TakeMedicineRepository takeMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;

    public String checkMedicine(EatMedicineInfo eatMedicineInfo) {
        Member member = memberRepository.findMemberByNickName(eatMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(eatMedicineInfo.getMedicineName());

        TakeMedicine takMedicine = takeMedicineRepository.findByWeekAndMedicineAndMember(
                eatMedicineInfo.getWeek(), medicine, member);

        EatMedicine eatMedicine = new EatMedicine(takMedicine, eatMedicineInfo.isChecked());
        eatMedicineRepository.save(eatMedicine);
        return "약 체크 완료";

    }

}
