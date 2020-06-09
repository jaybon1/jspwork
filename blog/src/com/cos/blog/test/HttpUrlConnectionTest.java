package com.cos.blog.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpUrlConnectionTest {
	public static void main(String[] args) {
		try {
//			URL url = new URL("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String temp;
//			int rank = 0;
//			while((temp = br.readLine()) != null) {
//				
//				System.out.println(temp);					
//				if(temp.contains("item_title")) {
//				}
////				if(temp.contains("<span class=\"keyword") && rank < 20){
////					System.out.println(++rank + "위:" + temp.split("<span class=\"keyword\">")[1].split("</span>")[0]);
////				}
//			}
//			con.disconnect();
//			br.close();

			Document d = Jsoup.connect("https://datalab.naver.com/keyword/realtimeList.naver").get();
			System.out.println(d.toString());

		} catch (Exception e) {
			System.out.println("에러처리");
			e.printStackTrace();
		}

	}
}