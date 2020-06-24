package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.model.PracticeTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;
import com.bitc.practiceProgress.util.Excel;
import com.bitc.practiceProgress.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PracticeTableAddExcelProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int classId;
		String realPath = request.getServletContext().getRealPath("excelfile");
		String contextPath = request.getServletContext().getContextPath();
		String fileName = null;
		String excelFile = null; // DB에 들어갈 변수 : 위치
		
		System.out.println(realPath);
		System.out.println(contextPath);
		
		try {
			MultipartRequest multi
			 = new MultipartRequest(request, realPath, 1024*1024*2, "utf-8", new DefaultFileRenamePolicy());
			
			if(multi.getFilesystemName("file") == null ||multi.getFilesystemName("file").equals("")) {
				Script.back("파일을 등록해주세요.", response);
				return;
			}
			
			fileName = multi.getFilesystemName("file");
			
			if(!fileName.substring(fileName.length()-5, fileName.length()).equals(".xlsx")) {
				Script.back("엑셀 파일이 아닙니다.", response);
				return;
			}
			
			classId = Integer.parseInt(multi.getParameter("id"));
			
			excelFile = realPath +"\\"+ fileName;
			
			List<PracticeTable> practiceTables = Excel.getList(excelFile);
			
			PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
			
			int result = practiceTableRepository.saveList(practiceTables, classId);
			
			if(result == 1) {
				
				ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
				
				int classIdResult = classTableRepository.updateExcelName(multi.getFileNames().nextElement().toString(), classId);
				
				if(classIdResult == 1) {
					Script.putScript("데이터 저장에 성공하였습니다.", "opener.location.reload(); window.close();", response);
				} else {
					Script.back("데이터 저장에 성공하였으나 파일명을 등록하지 못하였습니다. 훈련과정등록 페이지에서 수정해주세요.", response);
				}
				
			} else {
				Script.back("데이터 저장에 실패하였습니다.", response);
			}
			
			
//			if(result == 1) {
//				
//				HttpSession session = request.getSession();
//				Users principal = usersRepository.findById(id);
//				session.setAttribute("principal", principal);
//				
//				Script.href("사진변경 완료", "/blog/index.jsp", response);
//			} else {
//				Script.back("사진 변경 실패", response);
//			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
	}

}
