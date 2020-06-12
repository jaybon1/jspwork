package com.cos.blog.action.board;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cos.blog.action.Action;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			Script.back("잘못된 접근입니다.", response);
			return;
		}
		

		int boardId = Integer.parseInt(request.getParameter("id"));

		BoardRepository boardRepository = BoardRepository.getInstance();
		
		ReplyRepository replyRepository = ReplyRepository.getInstance();
		
		int result = 0;
		
		Cookie[] cookies = request.getCookies();
		int visitor = 0;
		
		
		
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName());
			if(cookie.getName().equals("visit")) {
				
				visitor = 1;
				
				
				if(cookie.getValue().contains(request.getParameter("id"))) {
					
					result = 1;
					
				} else {
					
					cookie.setValue(cookie.getValue()+ "_" + request.getParameter("id"));
					
					response.addCookie(cookie);
					
					result = boardRepository.readCountUp(boardId);
					
				} 
			}
		}
		
		if(visitor == 0) {
			Cookie cookie1 = new Cookie("visit", request.getParameter("id"));
			response.addCookie(cookie1);
			
			result = boardRepository.readCountUp(boardId);
		}
		
		
		
//		if(result != 1) {
//			Script.back("서버오류", response);
//			return;
//		}
		
		
//		Cookie cookie =
		
//		HttpSession session = request.getSession();
//		if(session.getAttribute("sameUser") == null || !session.getAttribute("sameUser").equals(request.getParameter("id"))) {
//			result = boardRepository.readCountUp(id);
//			session.setAttribute("sameUser", request.getParameter("id"));
//		}
		
		
		if(result == 1) {
			
			BoardResponseDto boardDto = boardRepository.findById(boardId);
			List<ReplyResponseDto> replyDtos = replyRepository.findAll(boardId);
			
			
			DetailResponseDto detailDto = DetailResponseDto.builder()
					.boardDto(boardDto)
					.replyDtos(replyDtos)
					.build();
			
			if (detailDto != null) {
				
				String content = detailDto.getBoardDto().getBoard().getContent(); // DTO에서 컨텐츠 가져오기
				
				String doc = HtmlParser.youtubeParser(content); // 유튜브 링크가 있다면 아래에 영상 프레임 넣기
				
				detailDto.getBoardDto().getBoard().setContent(doc); // 바뀐 내용을 콘텐츠에 바꿔넣기
				
				request.setAttribute("detailDto", detailDto);

				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
				
			} else {
				Script.back("잘못된 접근입니다.", response);
			}
		} else {
			Script.back("상세보기를 할 수 없습니다.", response);
		}
		
	}

}
