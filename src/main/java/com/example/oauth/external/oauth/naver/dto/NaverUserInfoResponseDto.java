package com.example.oauth.external.oauth.naver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaverUserInfoResponseDto {

    @JsonProperty("resultcode")
    private String resultCode;

    private String message;

    private Response response;

    @Getter @Setter
    public static class Response {

        private String email;

        private String nickname;

        @JsonProperty("profile_image")
        private String profileImage;

        private String age;

        private String gender;

        private String id;

        private String name;

        private String birthday;

        @JsonProperty("birthyear")
        private String birthYear;

        private String mobile;
    }
}
