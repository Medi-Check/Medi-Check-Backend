package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EatMedicineRepository extends JpaRepository<EatMedicine, Long> {

    @Query("select count(em) from EatMedicine em where em.checked = 1")
    Integer countByCheckedIsSuccess();

    @Query("select count(em) from EatMedicine em where em.checked = 0")
    Integer countByCheckedIsFail();

    @Query("select em from EatMedicine em join fetch em.takeMedicine join fetch em.takeMedicine.medicine where em.takeMedicine.week = :week "
            + "and date_format(em.createdAt, '%Y-%m-%d') = :today")
    List<EatMedicine> findAllByCreatedAt(Weekend week, String today);
}
