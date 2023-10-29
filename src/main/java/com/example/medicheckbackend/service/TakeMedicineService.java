package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineInfo;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TakeMedicineService {

    private final TakeMedicineRepository takeMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;

    public String insertTakeMedicine(TakeMedicineInfo takeMedicineInfo){
        Member member = memberRepository.findMemberByNickName(takeMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(takeMedicineInfo.getMedicineName());

        TakeMedicine takeMedicine = new TakeMedicine(takeMedicineInfo.getWeek(),
                takeMedicineInfo.getTime() , takeMedicineInfo.getAmounts(),
                medicine, member);

        takeMedicineRepository.save(takeMedicine);
        return "약 일정 저장 완료";
    }
}
