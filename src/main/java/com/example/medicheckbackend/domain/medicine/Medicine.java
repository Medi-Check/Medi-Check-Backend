package com.example.medicheckbackend.domain.medicine;

import com.example.medicheckbackend.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Medicine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String makeDate;

    @NotNull
    private String expirationDate;

    @NotNull
    private int amount;

    @NotNull
    private String information;

    private int medicineContainer;

    private int medicineCost;

    public Medicine(String name, String makeDate, String expirationDate, int amount, String information,
                    int medicineContainer, int medicineCost) {
        this.name = name;
        this.makeDate = makeDate;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.information = information;
        this.medicineContainer = medicineContainer;
        this.medicineCost = medicineCost;
    }

    public void modifyAmount(int amount) {
        this.amount = this.amount - amount;
    }
}
