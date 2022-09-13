package kr.human.camping;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import kr.human.camping.service.CSVService;
import kr.human.camping.service.CSVService2;




@SpringBootApplication
public class CampingProjectApplication {
	
	//@Autowired
	//CSVService csvService;
	
//
	//@Autowired
	//CSVService2 csvService2;

	
	public static void main(String[] args) {
		SpringApplication.run(CampingProjectApplication.class, args);
		
		
	}
	@Bean // 처음 BOOT가 실행될때 실행되는 함수
	public CommandLineRunner start1() throws Exception {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("CommandLineRunner 를 이용한 실행!!!");
				//웹브라우저 띄우기
				try {
					System.setProperty("java.awt.headless", "false");
					Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
					//csvService.saveDB(); //DB에 데이터를 저장하는 함수(캠핑장 업체 데이터)

					//csvService2.saveDB(); //DB에 데이터를 저장하는 함수(캠핑장 방 데이터)

				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		};
	}

}
