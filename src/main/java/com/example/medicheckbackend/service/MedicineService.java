package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.domain.medicine.dto.MedicineResponseDto.MedicineRes;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.repository.MedicineRepository;
import com.example.medicheckbackend.repository.MemberRepository;
import com.example.medicheckbackend.repository.TakeMedicineRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;
    private final TakeMedicineRepository takeMedicineRepository;

    public String insertMedicine(MedicineInfo medicineInfo) {
        Medicine medicine = new Medicine(medicineInfo.getName(), medicineInfo.getMakeDate(), medicineInfo.getExpirationDate(),
                medicineInfo.getAmount(), medicineInfo.getInformation(), medicineInfo.getMedicineContainer(),
                medicineInfo.getMedicineCost() );

        medicineRepository.save(medicine);
        return "약 정보 저장 완료";
    }

    public List<Medicine> selectAllMedicine () {
        return medicineRepository.findAll();
    }

    @Transactional
    public String deleteMedicineById(Long medicineId) {
        medicineRepository.findById(medicineId);
        return "약 정보 삭제 완료";
    }

    public List<MedicineRes> selectAllMedicineWithTake (String memberName) {
        Member member = memberRepository.findMemberByNickName(memberName);
        List<Medicine> medicine = medicineRepository.findAll();

        List<MedicineRes> medicineRes = new ArrayList<>();
        boolean check = true;
        for (int i = 0; i < medicine.size(); i++) {
            Integer count = takeMedicineRepository.countByMedicineAndMember(medicine.get(i), member);
            if(count == 0) check = false;
            else check = true;
            medicineRes.add(new MedicineRes(medicine.get(i), check));
        }

        return medicineRes;
    }

    @Transactional
    public String  updateImgUrl(String imageUrl, Long medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow();
        medicine.updateImgUrl(imageUrl);
        return "사진 추가 완료";
    }

}
