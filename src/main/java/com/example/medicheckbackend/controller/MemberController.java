package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController{
    private final MemberService memberService;

    /**
     * 별명 입력
     */
    @PostMapping("/member/nickname")
    public ResponseEntity<String> insertMember(@RequestBody MemberInfo memberInfo){
        return ResponseEntity.ok(memberService.insertMember(memberInfo));
    }

    /**
     * 가족 코드 전송
     */


}
