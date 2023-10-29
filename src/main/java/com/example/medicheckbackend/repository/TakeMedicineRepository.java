package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.Weekend;
import com.example.medicheckbackend.domain.medicine.Medicine;
import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.takemedicine.TakeMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeMedicineRepository extends JpaRepository<TakeMedicine, Long> {

    TakeMedicine findByWeekAndMedicineAndMember(Weekend week, Medicine medicine, Member member);
}
