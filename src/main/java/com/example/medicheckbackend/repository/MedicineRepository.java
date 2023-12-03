package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByName(String name);

    @Query("select count(m) from Medicine m where m.medicineContainer = :medicineContainer")
    Integer existsByMedicineContainer(int medicineContainer);
}
