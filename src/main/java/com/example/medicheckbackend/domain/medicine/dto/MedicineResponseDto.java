package com.example.medicheckbackend.domain.medicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MedicineResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicineRes {
        private String medicineName;
        private String information;
        private String makeDate;
        private String expirationDate;
        private int medicineContainer;
        private int amounts;
        private boolean takeMedicine;

        public MedicineRes(Medicine medicine, boolean takeMedicine) {
            this.medicineName = medicine.getName();
            this.information = medicine.getInformation();
            this.makeDate = medicine.getMakeDate();
            this.expirationDate = medicine.getExpirationDate();
            this.medicineContainer = medicine.getMedicineContainer();
            this.amounts = medicine.getAmount();
            this.takeMedicine = takeMedicine;
        }
    }
}
