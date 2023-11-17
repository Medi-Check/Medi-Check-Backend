package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineInfo;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineRequestDto.TakeMedicineWeek;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineResponseDto.TakeMedicineList;
import com.example.medicheckbackend.domain.takemedicine.dto.TakeMedicineResponseDto.TakeMedicineRes;
import com.example.medicheckbackend.service.TakeMedicineService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TakeMedicineController {

    private final TakeMedicineService takeMedicineService;

    /**
     * 약 일정 등록
     */
    @Operation(summary = "약 일정 등록")
    @PostMapping("/medicine/schedule")
    public ResponseEntity<String> insertSchedule(@RequestBody TakeMedicineInfo takeMedicineInfo) {
        return ResponseEntity.ok(takeMedicineService.insertTakeMedicine(takeMedicineInfo));
    }

    /**
     * 약 일정 모두 조회
     */
    @Operation(summary = "약 일정 모두 조회 (사람 이름에 따라)")
    @GetMapping("/member/schedules")
    public ResponseEntity<List<TakeMedicineRes>> selectSchedule(@RequestParam String memberName) {
        return ResponseEntity.ok(takeMedicineService.selectSchedule(memberName));
    }

    /**
     * 약 일정 요일 별로 조회
     */
    @Operation(summary = "약 일정 요일별로 조회")
    @GetMapping("/medicine/week")
    public ResponseEntity<List<TakeMedicineRes>> selectWeekSchedule(@RequestBody TakeMedicineWeek takeMedicineWeek) {
        return ResponseEntity.ok(takeMedicineService.selectWeekSchedule(takeMedicineWeek));
    }

    /**
     * 약 일정
     */
    @Operation(summary = "특정 약이름에 대한 모든 일정 조회")
    @GetMapping("/medicine/schedules")
    public ResponseEntity<List<TakeMedicineRes>> selectMedicineSchedules(@RequestParam String medicineName) {
        return ResponseEntity.ok(takeMedicineService.selectMedicineSchedules(medicineName));
    }

    /**
     * 약 정보 및 일정 모두 조회
     */
    @Operation(summary = "모든 약이름에 모든 일정 조회")
    @GetMapping("/medicine/take/schedules")
    public ResponseEntity<List<TakeMedicineList>> selectMedicineList() {
        return ResponseEntity.ok(takeMedicineService.selectMedicineList());
    }
}
