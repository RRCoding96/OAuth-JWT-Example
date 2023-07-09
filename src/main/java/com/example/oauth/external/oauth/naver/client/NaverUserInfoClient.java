package com.example.oauth.external.oauth.naver.client;

import com.example.oauth.external.oauth.naver.dto.NaverUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://openapi.naver.com", name = "naverUserInfoClient")
public interface NaverUserInfoClient {

    @GetMapping(value = "/v1/nid/me", consumes = "application/json")
    NaverUserInfoResponseDto getNaverUserInfo(@RequestHeader("Authorization") String accessToken);
}
