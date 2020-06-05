package com.cos.blog.model;

import java.sql.Timestamp;

import com.cos.blog.model.Users.UsersBuilder;
import com.nhncorp.lucy.security.xss.LucyXssFilter;
import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	
	public String getTitle() {
		return title;
	}
}
