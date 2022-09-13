package kr.human.camping.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import javax.websocket.Session;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class chatBotService {
	
	
	
	JSONObject chatbotobj = new JSONObject();
	
	// 초기 생성자 메서드 처음에만 실행된다.
	public JSONObject chatBotService() {
		log.info("chatBotService 생성자 메서드 실행!!!");
		chatbotobj.put("type", "message");
		chatbotobj.put("sessionId", "getId");
		chatbotobj.put("userName", "캠핑봇");
		chatbotobj.put("msg", "무엇을 도와드릴까요?");
		return chatbotobj;
	}
	
	
	// 챗봇 로직
	public void chatBot(JSONObject obj, WebSocketSession session) {
		log.info("chatBotService AI로직 실행!!!");
		String usermsg = obj.toString();
		System.out.println("user메시지 정보 : "+usermsg);
		WebSocketSession wss = session;
		try {
			wss.sendMessage(new TextMessage(usermsg));
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		String msg = (String)obj.get("msg");
		
		
		// 파일 읽기
		/*JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Reader reader = new FileReader("C:\\CampingProject\\src\\main\\resources\\static\\brain.json");
			jsonObject = (JSONObject) parser.parse(reader);
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
		
		String key = (String)jsonObject.get("로그인");
		*/
		if(msg.contains("로그인")) {
			chatbotobj.put("msg", "로그인 페이지는 <a href='local:8080/login'>로그인</a> 으로 접속해주세요.");
		} else if(msg.contains("안녕") || msg.contains("하이")) {
			chatbotobj.put("msg", "네, 안녕하세요.");			
		} else if(msg.contains("바보")) {
			chatbotobj.put("msg", "바보라고 하지마세요!");			
			
		} else if(msg.contains("멍청")) {
			chatbotobj.put("msg", "아직은 학습이 덜되었지만, 멍청이는 아닙니다!");			
			
		} else if(msg.contains("너") && msg.contains("이름")) {
			chatbotobj.put("msg", "제 이름은 캠핑봇 이에요.");			
			
		} else if(msg.contains("예약")) {
			chatbotobj.put("msg", "숙소 예약은 <a href='/serch'>여행지 둘러보기</a>에서 해당 여행지역 검색후 가고 싶은 숙소로 예약을 하시면 됩니다.");
			
		} else if(msg.contains("취소")) {
			chatbotobj.put("msg", "숙소 예약을 취소하시겠어요? 회경이에게 문의 하세요. 예약시스템 최종 담당자 입니다.");			
			
		} else if(msg.contains("캠핑")) {
			chatbotobj.put("msg", "캠핑은 정말 좋은것 같아요. 오늘 당장 놀러가는거 어때요?");			
			
		} else if(msg.contains("추천")) {
			chatbotobj.put("msg", "네, 캠핑장 추천을 해드릴께요. <a href='/search'>여행지 둘러보기</a>에서 찾아보시면 됩니다. ");			
			
		} else if(msg.contains("시발") || msg.contains("좆") || msg.contains("새끼") || msg.contains("개새") || msg.contains("씨발")) {
			chatbotobj.put("msg", "비속어는 안좋습니다. 비속어는 삼가해주세요.");
		} else {
			chatbotobj.put("msg", "아직은 무슨말인지 알수없습니다.");
			
		}
		
		
		
		try {
			TextMessage sendMsg = new TextMessage(chatbotobj.toJSONString());
			session.sendMessage(sendMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
