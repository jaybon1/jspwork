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
		DetailResponseDto drd = boardRepository.findById(id);

		if (drd != null) {
			String content = drd.getBoard().getContent();

			if (content.contains("youtube.com/")) {

				Document doc = Jsoup.parse(content);

				Elements els = doc.select("a");

				if (els.size() > 0) {

					Element el = els.get(0);

					String[] parseContent = el.attr("href").split("v=");

					System.out.println(parseContent[1]);

					StringBuilder sb = new StringBuilder();

					sb.append(content);
					sb.append("<br/>");
					sb.append("<iframe width=\"689\" height=\"517\" src=\"https://www.youtube.com/embed/"
							+ parseContent[1]
							+ "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");

					drd.getBoard().setContent(sb.toString());

				}

			} else if (content.contains("youtu.be/")) {

				Document doc = Jsoup.parse(content);

				Elements els = doc.select("a");

				if (els.size() > 0) {

					Element el = els.get(0);

					String[] parseContent = el.attr("href").split("be/");

					System.out.println(parseContent[1]);

					StringBuilder sb = new StringBuilder();

					sb.append(content);
					sb.append("<br/>");
					sb.append("<iframe width=\"689\" height=\"517\" src=\"https://www.youtube.com/embed/"
							+ parseContent[1]
							+ "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");

					drd.getBoard().setContent(sb.toString());

				}

			}

			request.setAttribute("dto", drd);

			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		} else {
			Script.back("잘못된 접근입니다.", response);
		}

	}

}
