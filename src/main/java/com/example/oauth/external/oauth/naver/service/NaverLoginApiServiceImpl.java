package com.example.oauth.external.oauth.naver.service;

import com.example.oauth.domain.member.constant.MemberType;
import com.example.oauth.external.oauth.model.OAuthAttributes;
import com.example.oauth.external.oauth.naver.client.NaverUserInfoClient;
import com.example.oauth.external.oauth.naver.dto.NaverUserInfoResponseDto;
import com.example.oauth.external.oauth.service.SocialLoginApiService;
import com.example.oauth.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NaverLoginApiServiceImpl implements SocialLoginApiService {

    private final NaverUserInfoClient naverUserInfoClient;

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        NaverUserInfoResponseDto naverUserInfoResponseDto = naverUserInfoClient.getNaverUserInfo(GrantType.BEARER.getType() + " " + accessToken);
        NaverUserInfoResponseDto.Response naverResponse = naverUserInfoResponseDto.getResponse();
        String email = naverResponse.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? naverResponse.getId() : email)
                .name(naverResponse.getName())
                .profile(naverResponse.getProfileImage())
                .memberType(MemberType.NAVER)
                .build();
    }
}
