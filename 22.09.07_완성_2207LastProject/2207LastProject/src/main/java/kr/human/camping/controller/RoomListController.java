package kr.human.camping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.ReservationService;
import kr.human.camping.service.RoomService;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RoomListController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ReservationService reservationService;
	
	// 방 목록보기
		@RequestMapping("/roomList")
		public String selectList(
				@RequestParam("idx") int idx, // 업체 리스트에서 받아와야함
				Model model 
				){
			List<RoomVO> list = roomService.selectRoomList(idx);
			CompanyVO cvo = reservationService.selectCompany(idx);
			model.addAttribute("list", list);
			model.addAttribute("cvo", cvo);
			log.info("list : " + list);
			return "reservation/roomList";
		}
}
