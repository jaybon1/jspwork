package com.cos.blog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.cos.blog.dto.NaverProfile;
import com.cos.blog.dto.NaverToken;
import com.google.gson.Gson;

public class NaverLogin {
	
	public static NaverToken getToken(String code) throws Exception {
		
		StringBuilder urlSb = new StringBuilder();
		
		urlSb.append("https://nid.naver.com/oauth2.0/token?");
		urlSb.append("client_id=qqoq10crroWmE1Yux6ZX&");
		urlSb.append("client_secret=5C8sZL6ECp&");
		urlSb.append("grant_type=authorization_code&state=1234567890&");
		urlSb.append("code=" + code);
		
		Map<String, String> requestHeaders = new HashMap<>();

        String responseBody = get(urlSb.toString(), requestHeaders);
        System.out.println(responseBody);
        
        Gson gson = new Gson();
        
        NaverToken naverToken = gson.fromJson(responseBody, NaverToken.class);
        return naverToken;
        
	}
	
	public static NaverProfile getProfile(String token) throws Exception {
        
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);
        
        Gson gson = new Gson();
        
        NaverProfile naverProfile = gson.fromJson(responseBody, NaverProfile.class);
		return naverProfile;
	}

    public static void main(String[] args) {
        String token = "YOUR_ACCESS_TOKEN"; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);

        System.out.println(responseBody);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}