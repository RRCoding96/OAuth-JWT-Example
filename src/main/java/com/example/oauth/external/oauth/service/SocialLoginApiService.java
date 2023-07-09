package com.example.oauth.external.oauth.service;


import com.example.oauth.external.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {

    OAuthAttributes getUserInfo(String accessToken);

}
