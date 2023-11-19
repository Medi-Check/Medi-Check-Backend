package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.service.EatMedicineService;
import com.example.medicheckbackend.service.MedicineService;
import com.example.medicheckbackend.service.MemberService;
import com.example.medicheckbackend.service.TakeMedicineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SelectController {

    private final MemberService memberService;
    private final TakeMedicineService takeMedicineService;
    private final EatMedicineService eatMedicineService;
    private final MedicineService medicineService;

    @GetMapping("/member")
    public ResponseEntity<List<Member>> selectAllMember() {
        return ResponseEntity.ok(memberService.selectAllMember());
    }

    @GetMapping("/medicine")
    public ResponseEntity<List<Medicine>> selectAllMedicine() {
        return ResponseEntity.ok(medicineService.selectAllMedicine());
    }

    @GetMapping("/takeMedicine")
    public ResponseEntity<List<TakeMedicine>> selectAllTakeMedicine() {
        return ResponseEntity.ok(takeMedicineService.selectAllTakeMedicine());
    }

    @GetMapping("/eatMedicine")
    public ResponseEntity<List<EatMedicine>> selectAllEatMedicine() {
        return ResponseEntity.ok(eatMedicineService.selectAllEatMedicine());
    }

}
