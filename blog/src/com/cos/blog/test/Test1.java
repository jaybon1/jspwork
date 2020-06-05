package com.cos.blog.test;

import com.nhncorp.lucy.security.xss.XssPreventer;

public class Test1 {
	public static void main(String[] args) {
		
		String clean = XssPreventer.escape("<asdfasdf>");
		
		System.out.println(clean);
	}
}
