package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.CSVService;
import kr.human.camping.service.SearchService;
import kr.human.camping.service.TestService;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchListPagingVO;
import kr.human.camping.vo.SelectRolePagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/today")
	public String today(Model model) {
		model.addAttribute("today", testService.today());
		return "today";
	}
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("serverTime", testService.today());
		model.addAttribute("today", testService.today());
		return "index";
	}
	
	@RequestMapping(value = "/map2")
	public String map2(Model model) {
		model.addAttribute("serverTime", testService.today());
		model.addAttribute("today", testService.today());
		return "map2";
	}
	
	// 전체 목록보기
	@RequestMapping("/test")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam(required = false, defaultValue = "") String keyword,
			Model model 
			){
		SearchListPagingVO<FileBoardVO> pagingVO = testService.selectList(keyword, p, s, b);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("keyword",keyword);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "test";
	}

	
}
