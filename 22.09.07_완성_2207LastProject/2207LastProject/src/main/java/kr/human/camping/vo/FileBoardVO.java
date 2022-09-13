package kr.human.camping.vo;

import java.util.Date;

import lombok.Data;

//CREATE TABLE fileBoard(
//	 	idx NUMBER PRIMARY KEY,
//		id varchar2(100) NOT NULL, 			-- 회원 및 관리자 계정 테이블의 id와 연결
//		subject varchar2(500) NOT NULL,
//		content varchar2(4000) NOT NULL,
//		regDate timestamp DEFAULT sysdate,
//		clickCount NUMBER(20) DEFAULT 0
//	);

@Data
public class FileBoardVO {
	private int idx;
	private String id;
	private String subject;
	private String content;
	private Date regDate;
	private String clickCount;
}
