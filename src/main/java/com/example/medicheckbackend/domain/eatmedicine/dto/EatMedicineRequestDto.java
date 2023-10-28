package com.example.medicheckbackend.domain.eatmedicine.dto;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EatMedicineRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EatMedicineInfo {
        private Weekend week;
        private String memberName;
        private String medicineName;
        private boolean checked;
    }

}