package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.TakeMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeMedicineRepository extends JpaRepository<TakeMedicine, Long> {
}
