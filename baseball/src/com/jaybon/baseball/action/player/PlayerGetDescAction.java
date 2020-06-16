package com.jaybon.baseball.action.player;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jaybon.baseball.action.Action;
import com.jaybon.baseball.model.Player;
import com.jaybon.baseball.repository.PlayerRepository;

public class PlayerGetDescAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String teamName = request.getParameter("teamName");
		int playerNum = Integer.parseInt(request.getParameter("playerNum"));
		
		
		PlayerRepository playerRepository = PlayerRepository.getInstance();
		
		Player player = playerRepository.findPlayerDesc(teamName, playerNum);
		
		Gson gson = new Gson();
		
		String playerJson = gson.toJson(player);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(playerJson);
		
	}

}
