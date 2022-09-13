package kr.human.camping.controller;


import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.sun.mail.iap.Response;

import kr.human.camping.email.MailService;
import kr.human.camping.service.MemberService;
import kr.human.camping.service.PasswordService;
import kr.human.camping.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller("MemberController")
@Log4j2
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MailService mailService;
	// 로그인
	// 회원정보가져오기
	// 로그아웃
	// 가입메일 전송하기
	// 임시비밀번호 발급
	// 
	
	@Bean
	public SpringSecurityDialect securityDialect(){
		return new SpringSecurityDialect();
	}
	
	
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Model model, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		MemberVO vo = new MemberVO();
		vo = (MemberVO)session.getAttribute("UserInfo");
		if(vo != null) {
			return "redirect:/";
		}
		if(error!=null) model.addAttribute("error","error");
		if(logout!=null) model.addAttribute("logout","logout");
		return "login";
	}
	
	// 관리자 로그인
//	@RequestMapping(value = "/login2", method = RequestMethod.GET)
//	public String login2(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Model model, HttpServletRequest request ) {
//		HttpSession session = request.getSession();
//		MemberVO vo = new MemberVO();
//		vo = (MemberVO)session.getAttribute("UserInfo");
//		if(vo != null && vo.getRole() == "admin") {
//			return "redirect:/";
//		}
//		if(error!=null) model.addAttribute("error","error");
//		if(logout!=null) model.addAttribute("logout","logout");
//		return "login2";
//	}
	
	// 회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertMemeber(@ModelAttribute MemberVO memberVO, Model model) {
		System.out.println(memberVO);
		memberService.MemberInsert(memberVO);
		model.addAttribute("memberVO",memberVO);
		
		return "welcome";
	} 
	
	// IdOverlap
	@RequestMapping(value = "/IdOverlap", method = RequestMethod.GET)
	@ResponseBody
	public int IdCheck(@RequestParam("id") String id, Model model) {
		System.out.println(id);
		int result = 0;
		result = memberService.IDOverlap(id);
		return result;
	}
	
	// EmailOverlap
	@RequestMapping(value = "/EmailOverlap", method = RequestMethod.GET)
	@ResponseBody
	public int EmailCheck(@ModelAttribute String email, Model model) {
		System.out.println(email);
		int result = 0;
		result = memberService.IDOverlap(email);
		return result;
	}
	
	/**
     * 로그인 실패 폼
     */
	@RequestMapping("/access_denied")
    public String accessDenied() {
        return "redirect:/login";
    }
	
	// 로그인 성공 폼
	@RequestMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        MemberVO userVo = (MemberVO) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("UserInfo", userVo); //유저 정보
        return "index";
    }
	
	
	@RequestMapping("/EmailCheck")
    public String welcome() {
      
        return "Emailwelcome";
    }
	
	// 이메일 인증 
	// 문제: 이메일에서 localhost로 링크가 안걸림
	@RequestMapping("/confilm")
    public String confilm(@RequestParam("id") String id) {
		// 해당 id를 인증하여 role을 user로 변환해준다.
		System.out.println(id);
		memberService.updateAccess(id);
        return "redirect:/login";
    }
	
	// 아이디 찾기
	@RequestMapping("/findID")
	public String findID(@RequestParam("email") String email, Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(model.getAttribute(email));
		map = memberService.findID(email);
		model.addAttribute("id",map.get("id"));
		model.addAttribute("name",map.get("name"));
		return "findIDpage";
	}
	
	// 비밀번호 찾기
	// map에는 id, email, name 을 넘겨준다. 
	@RequestMapping(value ="/findPassword", method = RequestMethod.POST)
	public String findPassword(@RequestParam HashMap<String, String> map, Model model) {
		// 새로운 비밀번호 생성
		String newPassword = PasswordService.makeNewPassword();
		System.out.println("새로운 비번: " + newPassword);
		System.out.println("이름: " + map.get("name"));
		System.out.println("Email: " + map.get("email"));
		System.out.println("ID: " + map.get("id"));
		// 새비밀번호를 넣어서 서비스로 GO!
		map.put("password", newPassword);
		memberService.changePassword(map);
		// 비밀번호 해당 email로 쏴주고
		map.put("password", newPassword);
		System.out.println(map);
		mailService.sendEmail(map);
		return "login";
	}
	
	// 내 정보 수정하기
	@RequestMapping("/updateInfo")
	public String updateInfo() {
		
		return "updateInfo";
	}
	
	// 아이디/비밀번호 찾기페이지
	@RequestMapping("/findpage")
	public String findpage() {
		
		return "findpage";
	}
	
	// 내 정보 수정하기페이지 이동
	@RequestMapping(value="/MemberInfoUpdate", method = RequestMethod.GET)
	public String MemberInfoUpdate(HttpServletRequest request) {
		System.out.println("MemeberInfoUpdate Controller");
		HttpSession session = request.getSession();
		System.out.println("session : "+session.getAttribute("UserInfo"));
		if(session.getAttribute("UserInfo") != null ) {
			return "MemberInfoUpdate";
		}
		return "/";
	}
	
	// 정보수정
	@RequestMapping(value="/InfoUpdate", method = RequestMethod.POST)
	public String passwordCheck(@ModelAttribute MemberVO memberVO, Model model) {
		System.out.println(memberVO);
		memberService.MemberUpdate(memberVO);
		
		return "MemberInfoUpdate";
	}
	
	
	
}
