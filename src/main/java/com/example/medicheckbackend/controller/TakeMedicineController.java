package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineInfo;
import com.example.medicheckbackend.service.TakeMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TakeMedicineController {

    private final TakeMedicineService takeMedicineService;
    /**
     * 약 일정 등록
     */
    @PostMapping("/medicine/schedule")
    public ResponseEntity<String> insertSchedule(@RequestBody TakeMedicineInfo takeMedicineInfo) {
        return ResponseEntity.ok(takeMedicineService.insertTakeMedicine(takeMedicineInfo));
    }

}
