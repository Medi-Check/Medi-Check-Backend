package com.example.medicheckbackend.service;

import com.example.medicheckbackend.domain.member.Member;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.domain.member.dto.MemberResponseDto.MemberRes;
import com.example.medicheckbackend.global.s3.S3Service;
import com.example.medicheckbackend.global.s3.dto.S3Result;
import com.example.medicheckbackend.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    public String insertMember(List<MultipartFile> multipartFile, MemberInfo memberInfo) {
        Member member;
        if(multipartFile.isEmpty()) {
            member = new Member(memberInfo.getNickName(), memberInfo.getFamilyCode(), "https://heronmovie.s3.ap-northeast-2.amazonaws.com/58f07179-e23b-45ff-9f9c-3368092f4054.png");
        }
        else {
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
}
