package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("keyword") == null ||
				request.getParameter("keyword").equals("")) {
			
			Script.back("검색 키워드가 없습니다.", response);
			return;
			
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		
//		System.out.println(Integer.parseInt(request.getParameter("page")));
		
		// 2. 3건만 페이징하여 가져오기
//		List<Board> boards = boardRepository.findAll();
//		List<Board> boards = boardRepository.findThree(Integer.parseInt(request.getParameter("page")));
		List<Board> boards = boardRepository.findAll(page, keyword);
		
		if(boards != null) {
			for (Board board : boards) {
				
				String preview = HtmlParser.getContentPreview(board.getContent());
				board.setContent(preview);
				
			}

		}
		
		int lastPage = (boardRepository.count(keyword) - 1) / 3;
		System.out.println(lastPage);
		System.out.println(request.getParameter("page"));
		
		request.setAttribute("boards", boards);
		request.setAttribute("lastPage", lastPage);
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		
	}

}
