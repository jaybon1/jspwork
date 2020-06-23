package com.bitc.practiceProgress.action.classTableAction;

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
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;

public class ClassTableHomeAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		ClassTableRepository progressRepository = ClassTableRepository.getInstance();
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();		
		
		List<ProgressInputDto> pids = progressRepository.findClassNameHomeroomProf();
		List<List<PracticeProgressDto>> ppdsList = practiceTableRepository.findPractice(classDate);
		
		request.setAttribute("pids", pids);
		request.setAttribute("ppdsList", ppdsList);
		
		RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
		dis.forward(request, response);
		
	}
}

