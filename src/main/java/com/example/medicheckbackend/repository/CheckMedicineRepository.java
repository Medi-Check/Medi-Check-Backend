package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.CheckMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckMedicineRepository extends JpaRepository<CheckMedicine, Long> {
}
