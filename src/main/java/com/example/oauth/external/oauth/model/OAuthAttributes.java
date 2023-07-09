package com.example.oauth.external.oauth.model;

import com.example.oauth.domain.member.constant.MemberType;
import com.example.oauth.domain.member.constant.Role;
import com.example.oauth.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter @Builder
public class OAuthAttributes { // 회원 정보 가져올 때 통일시킴

    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
                .profile(profile)
                .role(role)
                .build();
    }

}
