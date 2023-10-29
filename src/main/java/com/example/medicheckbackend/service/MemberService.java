package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.domain.member.dto.MemberResponseDto.MemberRes;
import com.example.medicheckbackend.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public String insertMember(MemberInfo memberInfo) {
        Member member = new Member(memberInfo.getNickName(), memberInfo.getFamilyCode());
        memberRepository.save(member);
        return "멤버 저장 완료";
    }

    public List<MemberRes> selectMembers(String familyCode) {
        return memberRepository.findAllByFamilyCode(familyCode).stream()
                .map(m -> new MemberRes(m.getNickName()))
                .collect(Collectors.toList());
    }
}
