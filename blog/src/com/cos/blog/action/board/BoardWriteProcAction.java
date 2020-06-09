package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		0번 인증 확인
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {

			Script.getMessage("잘못된 접근입니다.", response);
			return;

		}

//		1번 request에 title값과 content값 null인지 공백인지확인
		if (request.getParameter("title") == null || request.getParameter("title").equals("")) {

			Script.back("제목을 입력해주세요.", response);
			return;
			
		} else if (request.getParameter("content") == null || request.getParameter("content").equals("")) {

			Script.back("내용을 입력해주세요.", response);
			return;

		}

//		2번 request에 title값과 content, principal.getId()값 받기
		String title = request.getParameter("title");
		String content = request.getParameter("content") != null ? request.getParameter("content") : "";

		Users user = (Users) session.getAttribute("principal");

		int userId = user.getId();

		Board board = Board.builder().title(title).content(content).userId(userId).build();

//		3번 BoardRepository 연결해서 save(board객체) 함수 호출
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.save(board);

		if (result == 1) {
			Script.href("글 등록에 성공하였습니다.", "/blog/index.jsp", response);
		} else {
			Script.back("글 등록에 실패하였습니다. 제목과 내용을 확인하세요.", response);
		}

	}

}
