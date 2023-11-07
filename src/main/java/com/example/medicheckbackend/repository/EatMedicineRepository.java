package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EatMedicineRepository extends JpaRepository<EatMedicine, Long> {

    @Query("select count(em) from EatMedicine em where em.checked = 1")
    Integer countByCheckedIsSuccess();

    @Query("select count(em) from EatMedicine em where em.checked = 0")
    Integer countByCheckedIsFail();
}
