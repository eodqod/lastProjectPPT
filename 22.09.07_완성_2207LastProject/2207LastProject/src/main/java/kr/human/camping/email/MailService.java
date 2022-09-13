package kr.human.camping.email;

import java.util.HashMap;

public interface MailService {
	
	void sendEmail(String toAddress, String subject, String content);
	void sendEmail(HashMap<String, String> map);
}
