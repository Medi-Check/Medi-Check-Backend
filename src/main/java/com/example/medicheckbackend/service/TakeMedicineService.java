package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineInfo;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineWeek;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineResponseDto.TakeMedicineRes;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TakeMedicineService {

    private final TakeMedicineRepository takeMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;

    public String insertTakeMedicine(TakeMedicineInfo takeMedicineInfo) {
        Member member = memberRepository.findMemberByNickName(takeMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(takeMedicineInfo.getMedicineName());

        TakeMedicine takeMedicine = new TakeMedicine(takeMedicineInfo.getWeek(),
                takeMedicineInfo.getHour(), takeMedicineInfo.getMinute(), takeMedicineInfo.getAmounts(),
                medicine, member);

        takeMedicineRepository.save(takeMedicine);
        return "약 일정 저장 완료";
    }

    public List<TakeMedicineRes> selectSchedule(String memberName) {
        Member member = memberRepository.findMemberByNickName(memberName);

        return takeMedicineRepository.findAllByMember(member)
                .stream()
                .map(t -> new TakeMedicineRes(t.getMedicine().getName(), t.getWeek(),
                        t.getHour(), t.getMinute(), t.getAmounts())).collect(Collectors.toList());
    }

    public List<TakeMedicineRes> selectWeekSchedule(TakeMedicineWeek takeMedicineWeek) {
        Member member = memberRepository.findMemberByNickName(takeMedicineWeek.getMemberName());

        return takeMedicineRepository.findByWeekAndMember(takeMedicineWeek.getWeek(), member)
                .stream()
                .map(t -> new TakeMedicineRes(t.getMedicine().getName(), t.getWeek(),
                        t.getHour(), t.getMinute(), t.getAmounts())).collect(Collectors.toList());
    }
}
