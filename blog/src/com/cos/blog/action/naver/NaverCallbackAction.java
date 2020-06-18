package com.cos.blog.action.naver;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.dto.KakaoProfile;
import com.cos.blog.dto.KakaoToken;
import com.cos.blog.dto.NaverProfile;
import com.cos.blog.dto.NaverToken;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.KaKaoLogin;
import com.cos.blog.util.NaverLogin;
import com.cos.blog.util.Script;



public class NaverCallbackAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		
		if(code == null || code.equals("")) {
			Script.href("에러가 발생하였습니다. 홈으로 돌아갑니다.", "index.jsp", response);
			return;
		}
		
		System.out.println("네이버 인증 완료됨");
		System.out.println(code);
		System.out.println(state);

	
		// 토큰 요청하기
		NaverToken naverToken = null;
		try {
			naverToken = NaverLogin.getToken(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 프로필 요청하기
		NaverProfile naverProfile = null;
		try {
			naverProfile = NaverLogin.getProfile(naverToken.getAccess_token());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(naverProfile);
		
		
		// 회원가입 확인
		UsersRepository usersRepository = 
				UsersRepository.getInstance();
		
		Users principal = usersRepository.findByUsername(naverProfile.getResponse().getId()+"_naver");
		
		HttpSession session = request.getSession();
		
		if(principal != null) { // 기존회원이면 그냥 세션 넣고 로그인 진행
			
			session.setAttribute("principal", principal);
			Script.href("네이버 로그인 완료", "/blog/index.jsp", response);
			
		} else { // 기존회원이 아니면 회원가입 후 로그인 진행
			
			// email 값이 없으면 추가 회원정보 받으로 이동
			if(naverProfile.getResponse().getEmail() == null ||
					naverProfile.getResponse().getEmail().equals("")) {
				request.setAttribute("naverProfile", naverProfile);
				RequestDispatcher dis = 
						request.getRequestDispatcher("/user/naverOauthJoin.jsp");
				dis.forward(request, response);
				
			}else { // email 값이 있으면 바로 회원가입 및 로그인 진행
				String username = naverProfile.getResponse().getId() + "_naver";
				Users user = Users.builder()
						.username(username)
						.password(UUID.randomUUID().toString())
						.email(naverProfile.getResponse().getEmail())
						.userRole(RoleType.USER.toString())
						.build();
				usersRepository.save(user);
				session.setAttribute("principal", user);

				Script.href("네이버 회원가입 및 로그인 완료", "/blog/index.jsp", response);
			}
		}
	}
}
