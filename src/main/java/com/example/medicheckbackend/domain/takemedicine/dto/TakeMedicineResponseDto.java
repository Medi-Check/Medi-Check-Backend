package com.example.medicheckbackend.domain.takemedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TakeMedicineResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TakeMedicineRes {
        private String medicineName;
        private Long takeMedicineId;
        private Weekend week;
        private int hour;
        private int minute;
        private int amounts;

        public TakeMedicineRes(TakeMedicine takeMedicine) {
            this.medicineName = takeMedicine.getMedicine().getName();
            this.takeMedicineId = takeMedicine.getId();
            this.week = takeMedicine.getWeek();
            this.hour = takeMedicine.getHour();
            this.minute = takeMedicine.getMinute();
            this.amounts = takeMedicine.getAmounts();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TakeMedicineList {
        private String medicineName;
        List<TakeMedicineRes> takeMedicines = new ArrayList<>();
    }
}
