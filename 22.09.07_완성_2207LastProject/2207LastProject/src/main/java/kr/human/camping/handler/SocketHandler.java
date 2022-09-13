package kr.human.camping.handler;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.human.camping.service.chatBotService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SocketHandler extends TextWebSocketHandler {
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	@Autowired
	chatBotService chatbot = new chatBotService();
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지 발송
		log.info("handleTextMessage메서드 실행!(메세지 발송)");
		String msg = message.getPayload();
		System.out.println("메세지 내용 : " + msg);
		JSONObject json = JsonToObjectParser(msg);
		System.out.println(json);
		chatbot.chatBot(json, session);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		log.info("afterConnectionEstablished메서드 실행!!(소켓 연결) \n나의세션ID : " + session.getId());
		
		// 부모로 부터 상속받은 메서드를 통해 session값을 대입하여 연결시킨다.
		super.afterConnectionEstablished(session);
		session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
		// 연결된 세션정보를 hashmap에 저장한다. 
		sessionMap.put(session.getId(), session);
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
		JSONObject obj1 = chatbot.chatBotService(); // 소캣이 연결되면 챗봇의 생성자메소드가 실행된다.
		
		TextMessage sendMsg = new TextMessage(obj1.toJSONString());
		session.sendMessage(sendMsg);
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	// JSON파일이 들어오면 파싱해주는 함수
	private static JSONObject JsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	
}
