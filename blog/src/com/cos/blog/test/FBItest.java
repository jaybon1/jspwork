package com.cos.blog.test;

import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.repository.BoardRepository;

public class FBItest {
	public static void main(String[] args) {
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		DetailResponseDto drd = boardRepository.findById(1);
		
		System.out.println(drd.getUsername());
		
	}
}
