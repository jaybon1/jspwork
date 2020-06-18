package com.cos.blog.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.cos.blog.dto.KakaoProfile;
import com.cos.blog.dto.KakaoToken;
import com.google.gson.Gson;

public class KaKaoLogin {
	
	public static KakaoToken getToken(String code) throws Exception {
		
		
		// 엔드포인트
		String endpoint = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(endpoint);
		
		// 스트링빌더로 속성 추가
		StringBuilder outputSb = new StringBuilder();
		
		outputSb.append("grant_type=authorization_code&");
		outputSb.append("client_id=21a6b93175206a668eaa98bb2774a80a&");
		outputSb.append("redirect_uri=http://localhost:8000/blog/oauth/kakao?cmd=callback&");
		outputSb.append("code="+ code);
		
		// Stream 연결
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		
		// post 방식
		conn.setRequestMethod("POST");
		
		// 헤더 속성 추가
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 아웃풋 세팅
		conn.setDoOutput(true);

		// 데이터 전송
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(outputSb.toString());
		bw.flush();
		
		// 데이터 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		String input = "";
		
		StringBuilder inputSb = new StringBuilder();
		while((input = br.readLine()) != null) {
			inputSb.append(input);
		}
		System.out.println(inputSb.toString());
		// Gson으로 파싱
				Gson gson = new Gson();
				KakaoToken kakaoToken = gson.fromJson(inputSb.toString(), KakaoToken.class);
		
		return kakaoToken;
	}
	
public static KakaoProfile getProfile(KakaoToken kakaoToken) throws Exception {
		
		
		// 엔드포인트
		String endpoint = "https://kapi.kakao.com/v2/user/me";
		URL url = new URL(endpoint);
		
		// Stream 연결
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		
		// post 방식
		conn.setRequestMethod("POST");
		
		// 헤더 속성 추가
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setRequestProperty("Authorization", "Bearer "+kakaoToken.getAccess_token());
		
//		// 아웃풋 세팅
//		conn.setDoOutput(true);
		
		// 데이터 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		String input = "";
		
		StringBuilder inputSb = new StringBuilder();
		while((input = br.readLine()) != null) {
			inputSb.append(input);
		}
		System.out.println(inputSb.toString());
		// Gson으로 파싱
		Gson gson = new Gson();
		KakaoProfile kakaoProfile = gson.fromJson(inputSb.toString(), KakaoProfile.class);
		
		return kakaoProfile;
	}
}
