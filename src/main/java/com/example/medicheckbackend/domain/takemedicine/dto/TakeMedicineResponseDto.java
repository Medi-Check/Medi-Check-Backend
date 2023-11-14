package com.example.medicheckbackend.domain.takemedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TakeMedicineResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TakeMedicineRes {
        private String medicineName;
        private Weekend week;
        private int hour;
        private int minute;
        private int amounts;
    }
}
