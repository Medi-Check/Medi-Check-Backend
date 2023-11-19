package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.eatmedicine.EatMedicine;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TakeMedicineRepository extends JpaRepository<TakeMedicine, Long> {

    @Query("select tm from TakeMedicine tm join tm.medicine where tm.week = :week and tm.member = :member")
    List<TakeMedicine> findByWeekAndMember(Weekend week, Member member);

    @Query("select tm from TakeMedicine tm join fetch tm.medicine where tm.member = :member")
    List<TakeMedicine> findAllByMember(Member member);

    @Query("select tm from TakeMedicine tm join fetch tm.medicine join fetch tm.member")
    List<TakeMedicine> findAll();

    @Query("select tm from TakeMedicine tm join fetch tm.medicine where tm.week = :week "
            + "and date_format(tm.createdAt, '%Y-%m-%d') = :today")
    List<TakeMedicine> findAllByCreatedAt(Weekend week, String today);

    @Query("select tm from TakeMedicine tm join fetch tm.medicine where tm.medicine = :medicine")
    List<TakeMedicine> findAllByMedicine(Medicine medicine);

    @Query("select tm from TakeMedicine tm join fetch tm.medicine where tm.id = :takeMedicineId")
    TakeMedicine findByIdWithMedicine(Long takeMedicineId);

}
