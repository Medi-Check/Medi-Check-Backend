package com.example.medicheckbackend.domain.eatmedicine;

import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import com.example.medicheckbackend.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
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
public class EatMedicine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private TakeMedicine takeMedicine;

    @NotNull
    private int checked;

    private int healthRate;

    public EatMedicine(TakeMedicine takeMedicine, int checked) {
        this.takeMedicine = takeMedicine;
        this.checked = checked;
    }

    public void setHealthRate(int healthRate) {
        this.healthRate = healthRate;
    }
}
