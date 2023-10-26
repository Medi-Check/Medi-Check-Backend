package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.EatMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckMedicineRepository extends JpaRepository<EatMedicine, Long> {
}
