package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MedicineController {

    private final MedicineService medicineService;

    /**
     * 약 정보 등록
     */
    @PostMapping("/medicine")
    public ResponseEntity<String> insertMedicine(@RequestBody MedicineInfo medicineInfo,
                                                 @RequestParam int medicineContainer){
        return ResponseEntity.ok(medicineService.insertMedicine(medicineInfo, medicineContainer));
    }

}
