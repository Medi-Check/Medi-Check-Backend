package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.service.EatMedicineService;
import com.example.medicheckbackend.service.MedicineService;
import com.example.medicheckbackend.service.MemberService;
import com.example.medicheckbackend.service.TakeMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteController {

    private final MemberService memberService;
    private final TakeMedicineService takeMedicineService;
    private final EatMedicineService eatMedicineService;
    private final MedicineService medicineService;

    @PostMapping("/member/delete")
    public ResponseEntity<String> deleteMemberById(@RequestParam Long memberId) {
        return ResponseEntity.ok(memberService.deleteMemberById(memberId));
    }

    @PostMapping("/medicine/delete")
    public ResponseEntity<String> deleteMedicineById(@RequestParam Long medicineId) {
        return ResponseEntity.ok(medicineService.deleteMedicineById(medicineId));
    }

    @PostMapping("/takeMedicine/delete")
    public ResponseEntity<String> deleteTakeMedicineById(@RequestParam Long takeMedicineId) {
        return ResponseEntity.ok(takeMedicineService.deleteTakeMedicineById(takeMedicineId));
    }

    @PostMapping("/eatMedicine/delete")
    public ResponseEntity<String> deleteEatMedicineById(@RequestParam Long eatMedicineId) {
        return ResponseEntity.ok(eatMedicineService.deleteEatMedicineById(eatMedicineId));
    }

}
