package com.cos.blog.test;

import org.junit.Test;

import com.cos.blog.db.DBConn;

public class DBConnTest {
	
	@Test
	public void test1(){
		DBConn.getConnection();
	}
}

