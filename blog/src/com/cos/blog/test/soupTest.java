package com.cos.blog.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class soupTest {
	
	
	public static void htmlParser() {
		
		String content = "<p id='test'>asdf</p>";
		Document d = Jsoup.parse(content);
		System.out.println(d.toString());
		
//		Elements els = d.select("p");
//		
//		System.out.println(els.toString());
//		
//		Element el = els.get(0); // p태그의 첫번째
//		
//		System.out.println(el.text().toString());

	}

	public static void main(String[] args) {
		htmlParser();
	}
}

