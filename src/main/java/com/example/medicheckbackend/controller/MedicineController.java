package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MedicineController {

    private final MedicineService medicineService;

    /**
     * 약 정보 등록
     */
    @Operation(summary = "약 정보 등록")
    @PostMapping("/medicine")
    public ResponseEntity<String> insertMedicine(@RequestBody MedicineInfo medicineInfo){
        return ResponseEntity.ok(medicineService.insertMedicine(medicineInfo));
    }

}
