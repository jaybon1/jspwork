package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cos.blog.action.Action;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			Script.back("잘못된 접근입니다.", response);
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.readCountUp(id);
		
		if(result == 1) {
			DetailResponseDto drd = boardRepository.findById(id);
			if (drd != null) {
				
				String content = drd.getBoard().getContent(); // DTO에서 컨텐츠 가져오기
				
				String doc = HtmlParser.youtubeParser(content); // 유튜브 링크가 있다면 아래에 영상 프레임 넣기
				
				drd.getBoard().setContent(doc); // 바뀐 내용을 콘텐츠에 바꿔넣기
				
				request.setAttribute("dto", drd);

				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
			} else {
				Script.back("잘못된 접근입니다.", response);
			}
		}else {
			Script.back("상세보기를 할 수 없습니다.", response);
		}
		

		

	}

}
