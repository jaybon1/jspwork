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
import com.bitc.practiceProgress.repository.PracticeTableRepository;

public class ClassTableInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		
		int classTime = 1;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		List<Integer> idList = classTableRepository.findIdList();
		
		System.out.println(classDate);
		
		List<ProgressInputDto> pids = classTableRepository.findClassNameHomeroomProf();
		List<PracticeProgressDto> ppds = practiceTableRepository.findPracticeNow(classTime, classDate, idList);
		
		request.setAttribute("pids", pids);
		request.setAttribute("ppds", ppds);
		
		for (PracticeProgressDto practiceProgressDto : ppds) {
			System.out.println(practiceProgressDto.getSubject1());
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("/input/inputprogress.jsp");
		dis.forward(request, response);
	}
}

