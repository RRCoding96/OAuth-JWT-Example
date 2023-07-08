package com.example.oauth.jasypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    /**
     * 여기서 내 진짜 비밀번호들을 암호화해보고 그 결과를 yml 파일에 적어야 됨
     * 내 진짜 비밀번호들(kakao, naver 비밀키, db 비밀번 등등)은 따로 메모장 같은 곳에 적어놔야됨
     */
    @Test
    public void jasyptTest() {
        // 테스트를 돌려보기 위해서 VMOptions에 삽입해놓은 암호를 그대로 붙여넣음
        String password = "VMOptionPassword";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        String content = "secretkey";    // 암호화 할 내용
        String encryptedContent = encryptor.encrypt(content); // 암호화
        String decryptedContent = encryptor.decrypt(encryptedContent); // 복호화

        // 여기서 콘솔에 출력되는 값을 yml파일에 적어야 됨
        // 'ENC()' 꼭 써야됨!!
        System.out.println("Enc : " + encryptedContent + ", Dec: " + decryptedContent);

        // 암호화 한 결과를 yml 파일에 넣은 뒤에는 반드시 password에 하드코딩 한 암호를 지운 뒤 Git Commit & Push 해야 됨!!
    }

}
