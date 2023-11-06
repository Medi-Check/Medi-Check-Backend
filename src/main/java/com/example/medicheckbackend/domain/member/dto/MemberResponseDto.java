package com.example.medicheckbackend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberRes {
        private String nickName;
        private String imgUrl;
    }
}
