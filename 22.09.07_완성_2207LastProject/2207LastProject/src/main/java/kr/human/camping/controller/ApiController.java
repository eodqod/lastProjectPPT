package kr.human.camping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.human.camping.service.ReservationService;
import kr.human.camping.vo.ReservationVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class ApiController {

	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/list")
	public List<ReservationVO> roolList(@RequestParam(required = false ,defaultValue = "7") Integer roomidx){
		log.info("ApiController : {}", roomidx);
		return reservationService.selectReservationRoomList(roomidx);
	}
}
