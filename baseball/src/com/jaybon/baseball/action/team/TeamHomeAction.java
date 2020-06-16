package com.jaybon.baseball.action.team;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jaybon.baseball.action.Action;
import com.jaybon.baseball.model.Team;
import com.jaybon.baseball.repository.TeamRepository;

public class TeamHomeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TeamRepository teamRepository = TeamRepository.getInstance();
		
		List<Team> teamList = teamRepository.findAll();
		
		request.setAttribute("teamList", teamList);
		
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		
	}
	
}
