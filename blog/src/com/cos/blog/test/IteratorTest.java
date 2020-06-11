package com.cos.blog.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class IteratorTest {
	
	@Test
	public void 아이터_테스트() {
		ArrayList<Integer> aa = new ArrayList<>();
		aa.add(1);
		aa.add(2);
		aa.add(3);
		Iterator i = aa.iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
	}
}
