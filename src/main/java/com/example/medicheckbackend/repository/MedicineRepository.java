package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
