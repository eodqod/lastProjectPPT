package kr.human.camping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChattController {
	
	@RequestMapping("/chat")
	public ModelAndView chatt() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index"); // 채팅페이지명을 넣는다.
		return mv;
	}
}
