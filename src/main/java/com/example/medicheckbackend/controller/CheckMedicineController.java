package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.service.EatMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CheckMedicineController {

    private final EatMedicineService eatMedicineService;

    /**
     * 약 복용 체크
     */
    @PostMapping("/medicine/eat")
    public ResponseEntity<String> EatMedicine(@RequestBody EatMedicineInfo eatMedicineInfo){
        return ResponseEntity.ok(eatMedicineService.checkMedicine(eatMedicineInfo));
    }

    /**
     * 약 건강 체크
     */

}
