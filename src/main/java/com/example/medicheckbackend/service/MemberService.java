package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.FireBaseInfo;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.domain.member.dto.MemberResponseDto.MemberRes;
import com.example.medicheckbackend.global.s3.S3Service;
import com.example.medicheckbackend.global.s3.dto.S3Result;
import com.example.medicheckbackend.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    public String insertMember(List<MultipartFile> multipartFile, MemberInfo memberInfo) {
        Member member;
        if (multipartFile == null) {
            member = new Member(memberInfo.getNickName(), memberInfo.getFamilyCode(),
                    "https://heronmovie.s3.ap-northeast-2.amazonaws.com/7b331caf-413b-44b1-8f18-b66786ab2bc0.jpg");
        } else {
            List<S3Result> s3Results = s3Service.uploadFile(multipartFile);
            member = new Member(memberInfo.getNickName(), memberInfo.getFamilyCode(), s3Results.get(0).getImgUrl());
        }
        memberRepository.save(member);
        return "멤버 저장 완료";
    }

    public List<MemberRes> selectMembers(String familyCode) {
        return memberRepository.findAllByFamilyCode(familyCode).stream()
                .map(m -> new MemberRes(m.getNickName(), m.getImgUrl()))
                .collect(Collectors.toList());
    }

    @Transactional
    public String modifyFireBaseToken(FireBaseInfo fireBaseInfo) {
        Member member = memberRepository.findMemberByNickName(fireBaseInfo.getNickName());
        member.modifyFireBaseToken(fireBaseInfo.getFireBaseToken());
        return "토큰 추가 완료";
    }

    public List<Member> selectAllMember() {
        return memberRepository.findAll();
    }

    @Transactional
    public String deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
        return "member 삭제 완료";
    }
}
