package com.example.medicheckbackend.controller;

import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.FireBaseInfo;
import com.example.medicheckbackend.domain.member.dto.MemberRequestDto.MemberInfo;
import com.example.medicheckbackend.domain.member.dto.MemberResponseDto.MemberRes;
import com.example.medicheckbackend.global.mail.EmailService;
import com.example.medicheckbackend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final EmailService emailService;

    /**
     * 유저 등록
     */
    @Operation(summary = "유저 등록")
    @PostMapping("/member/nickname")
    public ResponseEntity<String> insertMember(@RequestPart(value = "images", required = false) List<MultipartFile> multipartFile,
                                               @RequestPart(value = "memberInfo") MemberInfo memberInfo) {
        return ResponseEntity.ok(memberService.insertMember(multipartFile, memberInfo));
    }

    /**
     * 가족 코드 전송
     */
    @Operation(summary = "가족 코드 전송")
    @PostMapping("/email/familyCode")
    public String emailSend(@RequestParam String email) throws Exception {
        String key = emailService.sendSimpleMessage(email);
        return key;
    }

    /**
     * 가족 코드가 같은 유저 모두 조회
     */
    @Operation(summary = "가족 코드가 같은 유저 모두 조회")
    @GetMapping("/members")
    public ResponseEntity<List<MemberRes>> selectMembers(@RequestParam String familyCode) {
        return ResponseEntity.ok(memberService.selectMembers(familyCode));
    }

    @Operation(summary = "firebase_token 업데이트")
    @PostMapping("/members/firebaseToken")
    public ResponseEntity<String> modifyFirebaseToken(@RequestBody FireBaseInfo fireBaseInfo) {
        return ResponseEntity.ok(memberService.modifyFireBaseToken(fireBaseInfo));
    }

}
