package com.cos.blog.action.user;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UsersProfileUploadProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getServletContext()는 프로젝트가 여러개일 때 무조건 넣어줘야함
		String realPath = request.getServletContext().getRealPath("image");
		int id;
		String contextPath = request.getServletContext().getContextPath();
		String fileName = null;
		String userProfile = null; // DB에 들어갈 변수 : 위치
		
		
		System.out.println("real " + realPath);
		System.out.println("context " + contextPath);
		
		try {
			MultipartRequest multi 
			= new MultipartRequest(request, realPath, 1024*1024*2, "utf-8", new DefaultFileRenamePolicy());
			
			fileName = multi.getFilesystemName("userProfile");
			
			System.out.println("fileName " + fileName);
			
			id = Integer.parseInt(multi.getParameter("id"));
			
			userProfile = contextPath + "/image/" + fileName;
			
			UsersRepository usersRepository = UsersRepository.getInstance();
			
			int result = usersRepository.update(id, userProfile);
			
			if(result == 1) {
				
				HttpSession session = request.getSession();
				Users principal = usersRepository.findById(id);
				session.setAttribute("principal", principal);
				
				Script.href("사진변경 완료", "/blog/index.jsp", response);
			} else {
				Script.back("사진 변경 실패", response);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
		
		
		
		
//		BufferedReader br = request.getReader();
//		StringBuilder sb = new StringBuilder();
//		
//		String input = null;
//		
//		while ((input = br.readLine()) != null) {
//			sb.append(input);
//		}
//		
//		System.out.println("사진받았음");
//		System.out.println(sb.toString());
//		Script.outText("테스트 중", response);
		
	}

}
