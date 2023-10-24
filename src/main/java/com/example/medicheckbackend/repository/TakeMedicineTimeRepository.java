package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.TakeMedicineTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeMedicineTimeRepository extends JpaRepository<TakeMedicineTime, Long> {
}
