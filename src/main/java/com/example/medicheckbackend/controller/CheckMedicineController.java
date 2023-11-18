package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.EatMedicineInfo;
import com.example.medicheckbackend.domain.eatmedicine.dto.EatMedicineRequestDto.HealthRateInfo;
import com.example.medicheckbackend.service.EatMedicineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Operation(summary = "약 복용 체크, DataHub 전송")
    @PostMapping("/medicine/eat")
    public ResponseEntity<Long> EatMedicine(@RequestBody EatMedicineInfo eatMedicineInfo){
        return ResponseEntity.ok(eatMedicineService.checkMedicine(eatMedicineInfo));
    }

    /**
     * 약 건강 체크
     */
    @Operation(summary = "약 건강 자동 체크, 사용 X")
    @GetMapping("/send")
    public void sendTest() {
        eatMedicineService.sendSuccess();
        eatMedicineService.sendFail();
    }

    /**
     * 약 주관적 건강 체크
     */
    @Operation(summary = "약 건강 체크 (1 ~ 5), DataHub 전송")
    @PostMapping("/medicine/healthRate")
    public ResponseEntity<String> CheckHealthMedicine(@RequestBody HealthRateInfo healthRateInfo) {
        return ResponseEntity.ok(eatMedicineService.healthRate(healthRateInfo));
    }

    /**
     * 전송 테스트
     */
    @Operation(summary = "오늘 약 낭비 비용 전송, DataHub 전송")
    @PostMapping("/send/worstCost")
    public ResponseEntity<String> sendDataMedicine() {
        return ResponseEntity.ok(eatMedicineService.sendSuccessAmount());
    }

}
