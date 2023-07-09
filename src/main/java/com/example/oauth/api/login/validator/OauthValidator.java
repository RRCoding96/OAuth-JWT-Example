package com.example.oauth.api.login.validator;

import com.example.oauth.domain.member.constant.MemberType;
import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class OauthValidator {

    public void validateMemberType(String memberType) {
        if(!MemberType.isMemberType(memberType)) {
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
        }
    }

}
