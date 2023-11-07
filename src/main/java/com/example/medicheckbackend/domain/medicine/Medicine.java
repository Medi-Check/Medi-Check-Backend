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
    private Integer amount;

    @NotNull
    private String information;

    private Integer medicineContainer;

    public Medicine(String name, String makeDate, Integer amount, String information, int medicineContainer) {
        this.name = name;
        this.makeDate = makeDate;
        this.amount = amount;
        this.information = information;
        this.medicineContainer = medicineContainer;
    }
}
