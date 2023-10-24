package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.TakeMedicineDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeMedicineDayRepository extends JpaRepository<TakeMedicineDay, Long> {
}
