package com.cos.blog.action.kakao;

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
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.KaKaoLogin;
import com.cos.blog.util.Script;



public class KakaoCallbackAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeError = request.getParameter("error");
		
		if(codeError != null) {
			Script.href("홈으로 돌아갑니다.", "index.jsp", response);
			return;
		}
		
		String code = request.getParameter("code");
		System.out.println("카카오 인증 완료됨");
		System.out.println(code);
		
		
		// 토큰 요청하기
		KakaoToken kakaoToken = null;
		try {
			kakaoToken = KaKaoLogin.getToken(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(kakaoToken);
		
		// 프로필 요청하기
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = KaKaoLogin.getProfile(kakaoToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(kakaoProfile);
		
		// 회원가입 확인
		UsersRepository usersRepository = 
				UsersRepository.getInstance();
		
		Users principal = usersRepository.findByUsername(kakaoProfile.getId()+"_kakao");
		
		HttpSession session = request.getSession();
		
		if(principal != null) { // 기존회원이면 그냥 세션 넣고 로그인 진행
			
			session.setAttribute("principal", principal);
			Script.href("카카오 로그인 완료", "/blog/index.jsp", response);
			
		} else { // 기존회원이 아니면 회원가입 후 로그인 진행
			
			// email 값이 없으면 추가 회원정보 받으로 이동
			if(kakaoProfile.getKakao_account().getEmail() == null ||
					kakaoProfile.getKakao_account().getEmail().equals("")) {
				request.setAttribute("kakaoProfile", kakaoProfile);
				RequestDispatcher dis = 
						request.getRequestDispatcher("/user/oauthJoin.jsp");
				dis.forward(request, response);
				
			}else { // email 값이 있으면 바로 회원가입 및 로그인 진행
				String username = kakaoProfile.getId() + "_kakao";
				Users user = Users.builder()
						.username(username)
						.password(UUID.randomUUID().toString())
						.email(kakaoProfile.getKakao_account().getEmail())
						.userRole(RoleType.USER.toString())
						.build();
				usersRepository.save(user);
				session.setAttribute("principal", user);

				Script.href("카카오 회원가입 및 로그인 완료", "/blog/index.jsp", response);
			}
		}
	}
}
