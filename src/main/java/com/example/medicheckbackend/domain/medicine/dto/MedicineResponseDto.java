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
        private Long medicineId;
        private String medicineName;
        private String information;
        private String makeDate;
        private String expirationDate;
        private int medicineContainer;
        private int amounts;
        private String imagUrl;
        private boolean takeMedicine;

        public MedicineRes(Medicine medicine, boolean takeMedicine) {
            this.medicineId = medicine.getId();
            this.medicineName = medicine.getName();
            this.information = medicine.getInformation();
            this.makeDate = medicine.getMakeDate();
            this.expirationDate = medicine.getExpirationDate();
            this.medicineContainer = medicine.getMedicineContainer();
            this.amounts = medicine.getAmount();
            this.imagUrl = medicine.getImageUrl();
            this.takeMedicine = takeMedicine;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicineContainerRes {
        private boolean first;
        private boolean second;
        private boolean third;
        private boolean fourth;
    }
}
