package com.example.oauth.api.login.service;

import com.example.oauth.api.login.dto.OauthLoginDto;
import com.example.oauth.domain.member.constant.MemberType;
import com.example.oauth.domain.member.constant.Role;
import com.example.oauth.domain.member.entity.Member;
import com.example.oauth.domain.member.service.MemberService;
import com.example.oauth.external.oauth.model.OAuthAttributes;
import com.example.oauth.external.oauth.service.SocialLoginApiService;
import com.example.oauth.external.oauth.service.SocialLoginApiServiceFactory;
import com.example.oauth.global.jwt.dto.JwtTokenDto;
import com.example.oauth.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}",  userInfo);

        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
        Member oauthMember;
        if(optionalMember.isEmpty()) { // 신규 회원 가입
            oauthMember = userInfo.toMemberEntity(memberType, Role.ADMIN);
            oauthMember = memberService.registerMember(oauthMember);
        } else { // 기존 회원일 경우
            oauthMember = optionalMember.get();
        }
        // 토큰 생성
        jwtTokenDto = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
        oauthMember.updateRefreshToken(jwtTokenDto);

        return OauthLoginDto.Response.of(jwtTokenDto);
    }

}
