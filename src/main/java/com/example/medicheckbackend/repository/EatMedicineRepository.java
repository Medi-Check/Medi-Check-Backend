package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatMedicineRepository extends JpaRepository<EatMedicine, Long> {
}
