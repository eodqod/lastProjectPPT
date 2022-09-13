package kr.human.camping.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CSVService2 {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void saveDB() {
		List<String> list = readCSV();
		System.out.println(list.size() + "개");
		// DB 연결
		
		for(int i=1;i<list.size();i++) {
			// DB에 저장하는 코드
			String lines[] = list.get(i).split(",",9);
			System.out.println(lines.length + "개 : " + Arrays.toString(lines));
			// VO를 만들어서 setter로 1개씩 넣기
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomidx(Integer.parseInt(lines[0]));
			roomVO.setIdx(Integer.parseInt(lines[1]));
			roomVO.setRoomname(lines[2]);
			roomVO.setMinpeople(Integer.parseInt(lines[3]));
			roomVO.setMaxpeople(Integer.parseInt(lines[4]));
			roomVO.setPrice(Integer.parseInt(lines[5]));
			roomVO.setR_check(Integer.parseInt(lines[6]));
			roomVO.setContent(lines[7]);
			roomVO.setCol1(lines[8]);
			try {
				int x = Integer.parseInt(lines[9]);
				roomVO.setCol2(x);		
			}catch (Exception e) {
				;
			}
			
			
			jdbcTemplate.update("insert into company_room values (company_room_roomidx_seq.nextval,?,?,?,?,?,?,?,?,?)",
								roomVO.getIdx(), roomVO.getRoomname(),roomVO.getMinpeople(),roomVO.getMaxpeople(),
								roomVO.getPrice(),roomVO.getR_check(),roomVO.getContent(),roomVO.getCol1(),roomVO.getCol2());
		}
		
		// DB닫기
	}
	
	public static List<String> readCSV(){
		List<String> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("src/main/resources/campingRoom.csv"),"MS949");
			while(sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return list;
	}
}
