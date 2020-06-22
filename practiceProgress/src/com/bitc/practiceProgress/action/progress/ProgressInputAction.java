package com.bitc.practiceProgress.action.progress;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.repository.ProgressRepository;

public class ProgressInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProgressRepository progressRepository = ProgressRepository.getInstance();
		
		List<ProgressInputDto> pids = progressRepository.findClassNameHomeroomProf();
		
		request.setAttribute("pids", pids);
		
		RequestDispatcher dis = request.getRequestDispatcher("input/inputprogress.jsp");
		dis.forward(request, response);
	}
}

