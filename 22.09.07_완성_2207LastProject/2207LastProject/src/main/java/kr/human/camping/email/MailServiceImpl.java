package kr.human.camping.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(String toAddress, String subject, String content) {
		MailHandler mailHandler=null;
		try {
			
			Context context = new Context();
			context.setVariable("name", subject);
			context.setVariable("id", content);
			String html = templateEngine.process("Emailwelcome", context);
			mailHandler = new MailHandler(javaMailSender);
			mailHandler.setFrom("hyunwoungkim@naver.com", "힐링캠핑"); //properties 에있는 자기 이메일이랑 맞춰야함
			mailHandler.setTo(toAddress); // 받는사람
			mailHandler.setSubject(subject + " 님 가입 축하드립니다. 인증 메일입니다."); // 제목
			mailHandler.setText(html);
			mailHandler.send(); // 메일 보내기
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendEmail(HashMap<String, String> map) {
		MailHandler mailHandler=null;
		try {
			
			Context context = new Context();
			context.setVariable("name", map.get("name"));
			context.setVariable("password", map.get("password"));
			String html = templateEngine.process("newPasswordEmail", context);
			mailHandler = new MailHandler(javaMailSender);
			mailHandler.setFrom("hyunwoungkim@naver.com", "힐링캠핑");
			mailHandler.setTo(map.get("email")); // 받는사람
			mailHandler.setSubject(map.get("name") + " 님 임시비밀번호 발급 메일입니다."); // 제목
			mailHandler.setText(html);
			mailHandler.send(); // 메일 보내기
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
