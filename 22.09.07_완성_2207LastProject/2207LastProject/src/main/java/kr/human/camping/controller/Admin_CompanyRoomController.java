package kr.human.camping.controller;


import java.util.List;

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
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Admin_CompanyRoomController {

	@Autowired
	private Admin_CompanyService admin_CompanyService;
	
	@Autowired
	private Admin_CompanyRoomService admin_CompanyRoomService;
	
	// 업체 방 전체 보기
	@RequestMapping("/CompanyRoomList")
	public String selectRoomList(@RequestParam("idx") int idx, Model model){
		List<RoomVO> list = admin_CompanyRoomService.selectRoomList(idx);
		CompanyVO cvo = admin_CompanyService.selectByIdx(idx);
		log.info("cvo 호출 : " + cvo);
		model.addAttribute("list", list);
		model.addAttribute("cvo", cvo);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomList";
	}

	// 객실 1개 내용보기
	@RequestMapping("/CompanyRoomView")
	public String selectRoomIdx(@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		model.addAttribute("vo", vo);
		log.info("방 1개 가져오기 : " + vo);
		return "admin/Company/CompanyRoomView";
	}
	
	// 객실 등록
	@RequestMapping("/CompanyRoomInsert")
	public String insert(@RequestParam("idx") int idx, Model model) {
		CompanyVO vo = admin_CompanyService.selectByIdx(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomInsert";
	}

	// 수정하기
	@RequestMapping("/CompanyRoomUpdate")
	public String selectByIdx(@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		log.info("vo 가져오기 : " + vo);
		model.addAttribute("vo", vo);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomUpdate";
	}
	
	// 삭제하기
	@RequestMapping("/CompanyRoomDelete")
	public String delete(@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		log.info("vo 가져오기 : " + vo);
		model.addAttribute("vo", vo);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomDelete";
	}

	// 저장/수정/삭제
	@RequestMapping(value = "/CompanyRoomUpdateOk", method = RequestMethod.GET)
	public String updateGet(@RequestParam("idx") int idx) {
		
		return "redirect:/CompanyRoomList?idx="+idx;
	}
	@RequestMapping(value = "/CompanyRoomUpdateOk", method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute CommVO commVO, @ModelAttribute RoomVO roomVO,
								@RequestParam("idx") int idx, Model model){
		boolean result = false;
		log.info("updateCompanyRoom : " + roomVO);
		log.info("updateCompanyRoom : " + commVO);
		log.info("등록 idx 가져오기 : " + idx);
		switch (commVO.getMode()) {
		case 1:
			result = admin_CompanyRoomService.insert(roomVO);
			log.info("insert 실행결과 : " + result);
			break;
		case 2:
			result = admin_CompanyRoomService.update(roomVO);
			log.info("update 실행결과 : " + result);
			break;
		case 3:
			result = admin_CompanyRoomService.delete(roomVO);
			log.info("delete 실행결과 : " + result);
			break;
		}
		return "redirect:/CompanyRoomList?idx="+idx;
	}

}
