package kr.human.camping.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.human.camping.vo.MemberVO;





@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("test", "sessionOK");
		System.out.println((MemberVO)authentication.getPrincipal());
		System.out.println(authentication.getPrincipal());
		session.setAttribute("UserInfo", (MemberVO)authentication.getPrincipal());
		System.out.println("session : "+session.getAttribute("UserInfo"));
		
		response.sendRedirect("/user_access"); 
	}

}
