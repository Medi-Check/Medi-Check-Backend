package com.example.medicheckbackend.domain.medicine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MedicineRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicineInfo {
        private String name;
        private String makeDate;
        private String expirationDate;
        private Integer amount;
        private String information;
        private int medicineContainer;
        private int medicineCost;
    }

}
