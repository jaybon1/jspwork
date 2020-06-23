package com.bitc.practiceProgress.action.classTableAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.util.Script;

public class ClassTableDetailDeleteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		int result = classTableRepository.delete(id);
		
		if(result == 1) {
			
			Script.putScript("삭제에 성공하였습니다.", "opener.location.reload(); window.close();", response);
			
		} else {
			Script.getMessage("삭제에 실패하였습니다.", response);
		}
		
	}

}
