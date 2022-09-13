package kr.human.camping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.FileBoardService;
import kr.human.camping.vo.CommVO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchListPagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileBoardController {

	@Autowired
	private FileBoardService fileBoardService;
	
	// 전체 목록보기
	@RequestMapping("/list")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam(required = false, defaultValue = "") String keyword,
			Model model 
			){
		SearchListPagingVO<FileBoardVO> pagingVO = fileBoardService.selectList(keyword, p, s, b);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("keyword",keyword);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Notice/list";
	}
	
	// 1개 내용보기
	@RequestMapping("/view")
	public String selectByIdx(@RequestParam("idx") int idx, Model model) {
		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
		model.addAttribute("vo", vo);
		return "admin/Notice/view";
	}
	
	// 새글쓰기
	@RequestMapping("/insert")
	public String insert(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model) {
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Notice/insert";
	}

	// 수정하기
	@RequestMapping("/update")
	public String selectByIdx(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("idx") int idx, Model model) {
		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Notice/update";
	}
	
	// 삭제하기
	@RequestMapping("/delete")
	public String delete(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("idx") int idx, Model model) {
		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Notice/delete";
	}

	// 저장/수정/삭제
	@RequestMapping(value = "/updateOk", method = RequestMethod.GET)
	public String updateGet() {
		
		return "redirect:/list";
	}
	@RequestMapping(value = "/updateOk", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute CommVO commVO, @ModelAttribute FileBoardVO fileBoardVO) {
		boolean result = false;
		log.info("updatePost : " + fileBoardVO);
		log.info("updatePost : " + commVO);
		switch (commVO.getMode()) {
		case 1:
			result = fileBoardService.insert(fileBoardVO);
			log.info("insert 실행결과 : " + result);
			break;
		case 2:
			result = fileBoardService.update(fileBoardVO);
			log.info("update 실행결과 : " + result);
			break;
		case 3:
			result = fileBoardService.delete(fileBoardVO);
			log.info("delete 실행결과 : " + result);
			break;
		}
//		return "admin/Notice/list";
		return "redirect:/list";
	}
		// 전체 목록보기
		@RequestMapping("/userBoard")
		public String selectUserBoard(
				@RequestParam(required = false, defaultValue = "1") int p,
				@RequestParam(required = false, defaultValue = "5") int s,
				@RequestParam(required = false, defaultValue = "5") int b,
				@RequestParam(required = false, defaultValue = "") String keyword,
				Model model 
				){
			SearchListPagingVO<FileBoardVO> pagingVO = fileBoardService.selectList(keyword, p, s, b);
			model.addAttribute("pv", pagingVO);
			model.addAttribute("p", p);
			model.addAttribute("s", s);
			model.addAttribute("b", b);
			model.addAttribute("keyword",keyword);
			model.addAttribute("br", "<br>");
			model.addAttribute("newLine", "\n");
			return "userBoard";
		}
		
		// 1개 내용보기
		@RequestMapping("/userBoardView")
		public String selectByUserBoardIdx(@RequestParam("idx") int idx, Model model) {
			FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
			model.addAttribute("vo", vo);
			return "userBoardView";
		}
}
