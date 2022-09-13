package kr.human.camping.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.human.camping.vo.CompanyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CSVService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void saveDB() {
		List<String> list = readCSV();
		System.out.println(list.size() + "개");
		// DB 연결
		
		for(int i=1;i<list.size();i++) {
			// DB에 저장하는 코드
			String lines[] = list.get(i).split(",",13);
			System.out.println(lines.length + "개 : " + Arrays.toString(lines));
			// VO를 만들어서 setter로 1개씩 넣기
			CompanyVO companyVO = new CompanyVO();
			companyVO.setIdx(Integer.parseInt(lines[0]));
			companyVO.setName(lines[1]);
			companyVO.setAdd1(lines[2]);
			companyVO.setAdd2(lines[3]);
			companyVO.setPostcode(Integer.parseInt(lines[4]));
			companyVO.setEco(lines[5]);
			companyVO.setRoomtype(lines[6]);
			companyVO.setTheme(lines[7]);
			companyVO.setAreacode(Integer.parseInt(lines[8]));
			companyVO.setDetailcode(Integer.parseInt(lines[9]));
			companyVO.setLatitude(Double.parseDouble(lines[10]));
			companyVO.setLongitude(Double.parseDouble(lines[11]));
			companyVO.setCol1(lines[12]);
			try {
				int x = Integer.parseInt(lines[13]);
				companyVO.setCol2(x);		
			}catch (Exception e) {
				;
			}
			
			
			jdbcTemplate.update("insert into company values (company_idx_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								companyVO.getName(), companyVO.getAdd1(), companyVO.getAdd2(),companyVO.getPostcode(),companyVO.getEco(),
								companyVO.getRoomtype(),companyVO.getTheme(),companyVO.getAreacode(),companyVO.getDetailcode(),companyVO.getLatitude(),companyVO.getLongitude(),
								companyVO.getCol1(),companyVO.getCol2());
		}
		
		// DB닫기
	}
	
	public static List<String> readCSV(){
		List<String> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("src/main/resources/camping.csv"),"MS949");
			while(sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return list;
	}
}
