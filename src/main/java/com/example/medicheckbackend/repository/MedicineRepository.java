package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByName(String name);
}
