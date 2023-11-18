package com.example.medicheckbackend.domain.eatmedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EatMedicineRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EatMedicineInfo {
        private Long takeMedicineId;
        private int checked;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HealthRateInfo {
        private int healthRate;
        private Long eatMedicineId;
    }

}
