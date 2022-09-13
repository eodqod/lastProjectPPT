package kr.human.camping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.Admin_CompanyRoomService;
import kr.human.camping.service.Admin_CompanyService;
import kr.human.camping.service.FileBoardService;
import kr.human.camping.vo.CommVO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Admin_CompanyController {

	@Autowired
	private Admin_CompanyService admin_CompanyService;
	
	// 전체 목록보기
	@RequestMapping("/CompanyList")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model 
			){
		PagingVO<CompanyVO> pagingVO = admin_CompanyService.selectList(p, s, b);
		model.addAttribute("pv", pagingVO);
		log.info("pagingVO : " + pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyList";
	}
	
	// 1개 내용보기
	@RequestMapping("/CompanyView")
	public String selectByIdx(@RequestParam("idx") int idx, Model model) {
		CompanyVO vo = admin_CompanyService.selectByIdx(idx);
		model.addAttribute("vo", vo);
		return "admin/Company/CompanyView";
	}
	
	// 새글쓰기
	@RequestMapping("/CompanyInsert")
	public String insert(
			@RequestParam(required = false, defaultValue = "1") Integer areacode,
			@RequestParam(required = false, defaultValue = "101") Integer detailcode,
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model) {
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);	
		model.addAttribute("areacode",areacode);
		model.addAttribute("detailcode",detailcode);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyInsert";
	}

	// 수정하기
	@RequestMapping("/CompanyUpdate")
	public String selectByIdx(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("idx") int idx, Model model) {
		CompanyVO vo = admin_CompanyService.selectByIdx(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyUpdate";
	}
	
	// 삭제하기
	@RequestMapping("/CompanyDelete")
	public String delete(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("idx") int idx, Model model) {
		CompanyVO vo = admin_CompanyService.selectByIdx(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyDelete";
	}

	// 저장/수정/삭제
	@RequestMapping(value = "/CompanyUpdateOk", method = RequestMethod.GET)
	public String updateGet() {
		
		return "redirect:/CompanyList";
	}
	@RequestMapping(value = "/CompanyUpdateOk", method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute CommVO commVO, @ModelAttribute CompanyVO companyVO) {
		boolean result = false;
		log.info("updateCompany : " + companyVO);
		log.info("updateCompany : " + commVO);
		switch (commVO.getMode()) {
		case 1:
			result = admin_CompanyService.insert(companyVO);
			log.info("insert 실행결과 : " + result);
			break;
		case 2:
			result = admin_CompanyService.update(companyVO);
			log.info("update 실행결과 : " + result);
			break;
		case 3:
			result = admin_CompanyService.delete(companyVO);
			log.info("delete 실행결과 : " + result);
			break;
		}
		return "redirect:/CompanyList";
	}

}
