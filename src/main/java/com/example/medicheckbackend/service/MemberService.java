package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public String insertMember(MemberInfo memberInfo){
        Member member = new Member(memberInfo.getNickName(), memberInfo.getFamilyCode());
        memberRepository.save(member);
        return "멤버 저장 완료";
    }
}
