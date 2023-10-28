package com.example.medicheckbackend.repository;

import com.example.medicheckbackend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findMemberByNickName(String name);
}
