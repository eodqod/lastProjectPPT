package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.Admin_MemberService;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SelectRolePagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Admin_MemberController {

	@Autowired
	private Admin_MemberService admin_MemberService;
	
	// 전체 목록보기
	@RequestMapping(value = "/MemberList")
	public String selectByMemberList(
			@RequestParam(required = false, defaultValue = "user") String role,
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model 
			){
		SelectRolePagingVO<MemberVO> pagingVO = admin_MemberService.selectByMemberList(role, p, s, b);
		log.info("pagingVO 호출 : " + pagingVO);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("role", role);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Member/MemberList";
	}

}
