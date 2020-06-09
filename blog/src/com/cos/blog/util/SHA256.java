package com.cos.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 해쉬암호 : 최근에는 대부분 SHA256을 사용한다.
// 해쉬는 복호화가 되지않는다.
// 암호화와 복호화가 되는 기술은 base64

// 해쉬와 해쉬를 비교하여 검증
// 해쉬는 고정길이라서 같은 값이 나올 수 있다 (충돌)
// 뚫릴 수 있다

// 레인보우 테이블

// 소금치기

public class SHA256 {
	
	private final static String mSalt = "코스"; // 임시 소금
	
	public static String encodeSha256(String source) {
		
        String result = "";
        
        byte[] a = source.getBytes(); // 소스를 바이트로 변경
        byte[] salt = mSalt.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            
            byte[] byteData = md.digest();
            
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result;
    };
}
