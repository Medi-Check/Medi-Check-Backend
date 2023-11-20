package com.example.medicheckbackend.domain.eatmedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EatMedicineResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EatMedicineRes {
        private String medicineName;
        private int medicineContainer;
        private Weekend week;
        private int hour;
        private int minute;
        private int healthRate;
        private int checked;

        public EatMedicineRes(TakeMedicine takeMedicine, EatMedicine eatMedicine) {
            this.medicineName = takeMedicine.getMedicine().getName();
            this.medicineContainer = takeMedicine.getMedicine().getMedicineContainer();
            this.week = takeMedicine.getWeek();
            this.hour = takeMedicine.getHour();
            this.minute = takeMedicine.getMinute();
            this.healthRate = eatMedicine.getHealthRate();
            this.checked = eatMedicine.getChecked();
        }
    }
}
