package kr.human.camping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.ReservationService;
import kr.human.camping.service.RoomService;
import kr.human.camping.vo.Comm2VO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.ReservationVO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservationController {

	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RoomService roomService;
	
	// 예약페이지
	@RequestMapping(value ="/reservation", method = {RequestMethod.GET})
		public String selectList(@RequestParam("roomidx") int roomidx, // 내가 선택한 방의 roomidx값
				Model model,
				HttpServletRequest request){
	
		HttpSession session = request.getSession(); // 세션영역에 있는 객체를 생성하여
		// 세션에 있는 MemberInfo를 vo에 담는다.
		MemberVO userVo = (MemberVO)session.getAttribute("UserInfo");  //userDetail 객체를 가져옴
		log.info("userVo 실행결과 : " + userVo);
		if(userVo != null) {
			int idx = roomService.companyidx(roomidx);
			RoomVO rvo = roomService.selectRoom(roomidx);
			CompanyVO cvo = reservationService.selectCompany(idx);
			
			model.addAttribute("userid", userVo.getId());      //유저 아이디	
			model.addAttribute("roomidx",roomidx);
			model.addAttribute("rvo",rvo);
			model.addAttribute("cvo",cvo);
			return "reservation/reservation";
		}
		return "/login";
	}
	// 나의예약페이지
		@RequestMapping(value ="/myreservation", method = {RequestMethod.GET})
			public String selectMyList(Model model,
					HttpServletRequest request){
			HttpSession session = request.getSession(); // 세션영역에 있는 객체를 생성하여
			// 세션에 있는 MemberInfo를 vo에 담는다.
			MemberVO userVo = (MemberVO)session.getAttribute("UserInfo");  //userDetail 객체를 가져옴
			log.info("userVo 실행결과 : " + userVo);
			if(userVo != null) {
				String id = userVo.getId();
				List<ReservationVO> list = reservationService.selectMyReservation(id);
				model.addAttribute("userid", userVo.getId());     //유저 아이디	
				model.addAttribute("list", list);     // 나의 예약 리스트
								
		
				return "reservation/myreservation";
			}
			return "/login";
		}
	
	// 저장/삭제 
		@RequestMapping(value = "reservationupdate", method = RequestMethod.GET)
		public String updateGet() {
			
			return "redirect:/search";
		}
		
		@RequestMapping(value = "reservationupdate", method = RequestMethod.POST)
		@ResponseBody
		public String updatePost(@ModelAttribute Comm2VO comm2VO, @ModelAttribute ReservationVO reservationVO) {
			boolean result = false;
			log.info("updatePost : " + reservationVO);
			log.info("reservationVO : " + reservationVO.getRoomidx());
			log.info("updatePost : " + comm2VO);

//			CompanyVO cvo = reservationService.selectCompany(reservationVO.getRoomidx());
//			log.info("cvo : " + cvo);
//			int idx = cvo.getIdx();
//			log.info("idx : " + cvo.getIdx());

			switch (comm2VO.getMode()) {
			case "insert":
				result = reservationService.insertReservation(reservationVO);
				log.info("insertReservation 실행결과 : " + result);
				break;
			case "delete":
				result = reservationService.deleteReservation(reservationVO);
				log.info("deleteReservation 실행결과 : " + result);
				break;
			}
			return "/search";
		}
}
