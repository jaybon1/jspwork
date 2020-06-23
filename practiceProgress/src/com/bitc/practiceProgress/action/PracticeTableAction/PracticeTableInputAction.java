package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.repository.PracticeTableRepository;

public class PracticeTableInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		List<List<PracticeProgressDto>> ppdsList = practiceTableRepository.findPractice(classDate);
		
		request.setAttribute("ppdsList", ppdsList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/input/inputdata.jsp");
		rd.forward(request, response);
		
	}
	
}
