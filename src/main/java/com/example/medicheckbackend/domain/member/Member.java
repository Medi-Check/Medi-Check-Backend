package com.example.medicheckbackend.domain.member;

import com.example.medicheckbackend.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.transaction.annotation.Transactional;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nickName;

    @NotNull
    private String familyCode;

    private String imgUrl;

    private String firebaseToken;

    public Member(String nickName, String familyCode, String imgUrl) {
        this.nickName = nickName;
        this.familyCode = familyCode;
        this.imgUrl = imgUrl;
    }

    @Transactional
    public void modifyFireBaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
