package com.cos.blog.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		0번 인증 확인
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {

			Script.getMessage("잘못된 접근입니다.", response);
			return;

		}
		
//		1번 request에 title값과 content값 null인지 공백인지확인
		if (request.getParameter("id") == null || request.getParameter("id").equals("")){

			Script.back("잘못된 접근입니다.", response);
			return;

		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.deleteById(id);
		
		Script.outText(result + "", response);
	}
}

