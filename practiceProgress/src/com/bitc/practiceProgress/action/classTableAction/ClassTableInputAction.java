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
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.repository.ClassTableRepository;

public class ClassTableInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassTableRepository progressRepository = ClassTableRepository.getInstance();
		
		int classTime = 0;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		List<ProgressInputDto> pids = progressRepository.findClassNameHomeroomProf();
		List<PracticeProgressDto> ppds = progressRepository.findPracticeNow(classTime, classDate);
		
		request.setAttribute("pids", pids);
		request.setAttribute("ppds", ppds);
		
		RequestDispatcher dis = request.getRequestDispatcher("/input/inputprogress.jsp");
		dis.forward(request, response);
	}
}

