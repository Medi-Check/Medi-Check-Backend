package com.example.medicheckbackend.domain.takemedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TakeMedicineRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TakeMedicineInfo {
        private Weekend week;
        private String medicineName;
        private String memberName;
        private int hour;
        private int minute;
        private int amounts;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TakeMedicineWeek {
        private Weekend week;
        private String memberName;
    }
}
