package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineInfo;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineResponseDto.TakeMedicineList;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineResponseDto.TakeMedicineRes;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TakeMedicineService {

    private final TakeMedicineRepository takeMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;

    public String insertTakeMedicine(TakeMedicineInfo takeMedicineInfo) {
        Member member = memberRepository.findMemberByNickName(takeMedicineInfo.getMemberName());
        Medicine medicine = medicineRepository.findByName(takeMedicineInfo.getMedicineName());

        List<TakeMedicine> takeMedicines = new ArrayList<>();

        for (int i = 0; i < takeMedicineInfo.getWeek().length; i++) {
            for(int j = 0; j < takeMedicineInfo.getTime().length; j++) {
                String[] temp = takeMedicineInfo.getTime()[j].split(":");
                int hour = Integer.parseInt(temp[0]);
                int minute = Integer.parseInt(temp[1]);
                takeMedicines.add(new TakeMedicine(takeMedicineInfo.getWeek()[i],
                        hour, minute, takeMedicineInfo.getTakeAmount(), medicine, member));
            }
        }

        takeMedicineRepository.saveAll(takeMedicines);
        return "약 일정 저장 완료";
    }

    public List<TakeMedicineRes> selectSchedule(String memberName) {
        Member member = memberRepository.findMemberByNickName(memberName);

        return takeMedicineRepository.findAllByMember(member)
                .stream()
                .map(TakeMedicineRes::new)
                .collect(Collectors.toList());
    }

    public List<TakeMedicineRes> selectWeekSchedule(String week, String memberName) {
        Member member = memberRepository.findMemberByNickName(memberName);

        return takeMedicineRepository.findByWeekAndMember(Weekend.valueOf(week), member)
                .stream()
                .map(TakeMedicineRes::new)
                .sorted((t1, t2) -> {
                    if(t1.isStatus() && !t2.isStatus()) return -1;
                    if(!t1.isStatus() && t2.isStatus()) return  1;
                    if(t1.getHour() == t2.getHour()) return t1.getMinute() - t2.getMinute();
                    return t1.getHour() - t2.getHour();
                })
                .collect(Collectors.toList());
    }

    public List<TakeMedicineRes> selectMedicineSchedules(String medicineName) {
        Medicine medicine = medicineRepository.findByName(medicineName);
        List<TakeMedicine> takeMedicines = takeMedicineRepository.findAllByMedicine(medicine);

        return takeMedicines.stream()
                .map(TakeMedicineRes::new)
                .collect(Collectors.toList());
    }

    public List<TakeMedicineList> selectMedicineList() {
        List<Medicine> medicines = medicineRepository.findAll();
        List<TakeMedicineList> takeMedicineLists = new ArrayList<>();

        for (int i = 0; i < medicines.size() ; i++) {
            List<TakeMedicine> takeMedicines = takeMedicineRepository.findAllByMedicine(medicines.get(i));
            takeMedicineLists.add(new TakeMedicineList(medicines.get(i).getName(),
                    takeMedicines.stream().map(TakeMedicineRes::new).collect(Collectors.toList())));
        }

        return takeMedicineLists;
    }

    public List<TakeMedicine> selectAllTakeMedicine () {
        return takeMedicineRepository.findAll();
    }

    @Transactional
    public String deleteTakeMedicineById(Long takeMedicineId) {
        takeMedicineRepository.findById(takeMedicineId);
        return "약 일정 삭제 완료";
    }

    @Transactional
    public String modifyMedicineFalse(Long takeMedicineId) {
        TakeMedicine takeMedicine = takeMedicineRepository.findById(takeMedicineId).orElseThrow();
        takeMedicine.setStatusFalse();
        return "약 일정 확인";
    }

    public List<TakeMedicineRes> getTakeMedicineRes() {
        List<TakeMedicine> takeMedicine = takeMedicineRepository.findAll();

        return takeMedicine
                .stream()
                .map(TakeMedicineRes::new)
                .collect(Collectors.toList());
    }
}
