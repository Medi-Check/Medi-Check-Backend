package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TakeMedicineRepository extends JpaRepository<TakeMedicine, Long> {

    TakeMedicine findByWeekAndMedicineAndMemberAndTime(Weekend week, Medicine medicine, Member member, String time);

    List<TakeMedicine> findByWeekAndMember(Weekend week, Member member);

    @Query("select tm from TakeMedicine tm join fetch tm.medicine where tm.member = :member")
    List<TakeMedicine> findAllByMember(Member member);

}
