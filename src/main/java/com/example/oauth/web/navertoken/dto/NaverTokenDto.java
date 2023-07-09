package com.example.oauth.web.navertoken.dto;

import lombok.Builder;
import lombok.Getter;

public class NaverTokenDto {

    @Builder @Getter
    public static class Request {

        private String grant_type;
        private String client_id;
        private String client_secret;
        private String code;
        private String state;
    }

    @Builder @Getter
    public static class Response {

        private String access_token;
        private String refresh_token;
        private String token_type;
        private Integer expires_in;
        private String error;
        private String error_description;
    }
}
