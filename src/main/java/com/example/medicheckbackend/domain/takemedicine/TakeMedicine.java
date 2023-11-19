package com.example.medicheckbackend.domain.takemedicine;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TakeMedicine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Weekend week;

    @NotNull
    private int hour;

    private int minute;

    @NotNull
    private int amounts;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Medicine medicine;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public TakeMedicine(Weekend week, int hour, int minute, int amounts, Medicine medicine, Member member) {
        this.week = week;
        this.hour = hour;
        this.minute = minute;
        this.amounts = amounts;
        this.medicine = medicine;
        this.member = member;
    }
}
