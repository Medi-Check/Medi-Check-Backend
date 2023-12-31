package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.medicine.dto.MedicineRequestDto.MedicineInfo;
import com.example.medicheckbackend.domain.medicine.dto.MedicineResponseDto.MedicineContainerRes;
import com.example.medicheckbackend.domain.medicine.dto.MedicineResponseDto.MedicineRes;
import com.example.medicheckbackend.service.MedicineService;
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

    /**
     * 약 일정 등록했는지 확인
     */
    @Operation(summary = "약 모두 조회 (일정이 등록 되어 있으면 true 리턴)")
    @GetMapping("/medicine/check/take")
    public ResponseEntity<List<MedicineRes>> selectAllMedicineWithTake(@RequestParam String memberName) {
        return ResponseEntity.ok(medicineService.selectAllMedicineWithTake(memberName));
    }

    /**
     * 약 container 등록
     */
    @Operation(summary = "약통 등록")
    @PostMapping("/medicine/container")
    public ResponseEntity<String> updateMedicineContainer(@RequestParam Long medicineId, int containerId){
        return ResponseEntity.ok(medicineService.updateMedicineContainer(containerId, medicineId));
    }

    /**
     * 약 Container 정보 알기
     */
    @Operation(summary = "모든 약통 정보 확인")
    @GetMapping("/medicine/container")
    public ResponseEntity<MedicineContainerRes> selectAllMedicineContainer() {
        return ResponseEntity.ok(medicineService.selectAllMedicineContainer());
    }

}
