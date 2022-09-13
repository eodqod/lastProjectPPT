-- 회원 및 관리자 계정 테이블
CREATE SEQUENCE member_idx_seq;
SELECT * FROM "MEMBER" m ;
CREATE TABLE MEMBER(
	id varchar2(100) PRIMARY KEY,
	idx NUMBER,
	password varchar2(100) NOT NULL,
	name varchar2(100) NOT NULL,
	phone varchar2(20) NOT NULL,
	email varchar2(100) NOT NULL,
	gender char(1) check(gender IN('0','1'))NOT NULL,
	col1 varchar2(100),
	col2 NUMBER,
	col3 varchar2(100)
);

INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user01', member_idx_seq.nextval, '1234', '한사람', 01053244561, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user02', member_idx_seq.nextval, '1234', '두사람', 01243453266, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user03', member_idx_seq.nextval, '1234', '세사람', 12434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user04', member_idx_seq.nextval, '1234', '네사람', 12434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user05', member_idx_seq.nextval, '1234', '오사람', 12434532663, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user06', member_idx_seq.nextval, '1234', '육사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user07', member_idx_seq.nextval, '1234', '칠사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user08', member_idx_seq.nextval, '1234', '팔사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user09', member_idx_seq.nextval, '1234', '구사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user10', member_idx_seq.nextval, '1234', '십사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('user11', member_idx_seq.nextval, '1234', '십일사람', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '0', '', 0, '');
INSERT INTO JSPUSER."MEMBER"
(ID, IDX, PASSWORD, NAME, PHONE, EMAIL, GENDER, COL1, COL2, COL3)
VALUES('admin', member_idx_seq.nextval, '1234', '관리자일', 02434532663, 'ㅁㄴㅇㄹ@asdf.com', '1', '', 0, '');

select m.name, m.id, m.phone, m.email, m.gender, member_role.role from MEMBER m full outer join member_role on m.id=member_role.id and role='user' order by m.idx DESC;
SELECT * FROM MEMBER_ROLE mr ;
-- 회원 및 관리자 계정 권한 나누는 테이블
CREATE SEQUENCE member_role_idx_seq;
CREATE TABLE member_role(
	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL,			-- 회원 및 관리자 계정 테이블의 id와 연결
	ROLE varchar2(100) NOT NULL,
	col1 varchar2(100),
	col2 NUMBER
);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user01', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user01', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user02', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user03', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user03', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user02', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user04', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user04', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user05', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user05', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user06', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user06', 'dormancy', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user07', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user08', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user09', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user10', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'user11', 'user', '', 0);
INSERT INTO JSPUSER.MEMBER_ROLE
(IDX, ID, "ROLE", COL1, COL2)
VALUES(member_role_idx_seq.nextval, 'admin', 'admin', '', 0);
UPDATE JSPUSER.MEMBER_ROLE
<<<<<<< HEAD
SET "ROLE"='admin'
WHERE IDX=0;
=======
SET ID='chhg9999', "ROLE"='admin', COL1='', COL2=0
WHERE IDX=42;
>>>>>>> hoegyeung99


select m.name, m.id, m.phone, m.email, m.gender, member_role.role 
	from MEMBER m full outer join member_role 
	on m.id=member_role.id 
	and not role='admin' 
	order by m.idx DESC;

SELECT DISTINCT m.name, m.id, m.phone, m.email, m.gender, mr.role
	FROM "MEMBER" m, MEMBER_ROLE mr 
	WHERE mr."ROLE" = 'user'
	AND m.id=mr.id;

select * from member_role where role='dormancy';

SELECT * FROM MEMBER_ROLE mr ;



SELECT * FROM member;


-- 업체 테이블
CREATE SEQUENCE company_idx_seq;
DROP SEQUENCE company_idx_seq;
DROP TABLE COMPANY;
SELECT * FROM COMPANY c ;
CREATE table company(
	idx NUMBER PRIMARY KEY,
	name varchar2(100) NOT null,
	add1 varchar2(100) NOT NULL,
	add2 varchar2(100) NOT NULL,
	postcode number(20) NOT NULL,
	eco varchar2(100) NOT NULL,
	roomtype varchar2(100) NOT NULL,
	theme varchar2(100) NOT NULL,
	areacode number(10) NOT NULL,		-- 전국지도
	detailcode number(10) NOT NULL,     -- 세부지역
	Latitude float(30) NOT NULL,
	longitude float(30) NOT NULL,
	col1 varchar2(100),
	col2 number
);

-- 업체 방 테이블

CREATE SEQUENCE company_room_roomidx_seq;
DROP TABLE COMPANY_ROOM;
DROP SEQUENCE company_room_roomidx_seq;
SELECT * FROM COMPANY_ROOM;
CREATE TABLE company_room(
	roomidx number PRIMARY KEY,
	idx NUMBER NOT NULL,				-- 업체 테이블의 idx와 연결
	roomname varchar2(100) NOT NULL,
	minpeople number(2) NOT NULL,
	maxpeople number(2) NOT NULL,
	price number(10) NOT NULL,
	r_check char(1) check(r_check IN('0','1'))NOT NULL,
	content varchar2(4000),
	col1 varchar2(100),
	col2 number
);
INSERT INTO JSPUSER.COMPANY_ROOM
(ROOMIDX, IDX, ROOMNAME, MINPEOPLE, MAXPEOPLE, PRICE, R_CHECK, CONTENT, COL1, COL2)
VALUES(company_room_roomidx_seq.nextval, 3961, '테스트1', 1, 6, 30000, '0', '테스트1내용', '', 0);

SELECT * FROM company_room WHERE idx=(SELECT idx FROM company c WHERE idx=3961);

--  예약 테이블
DROP TABLE reservation;
SELECT * FROM RESERVATION r ;
CREATE TABLE reservation(
	id varchar2(100) NOT NULL,					-- 회원 및 관리자 계정 테이블의 id와 연결
	roomidx number NOT NULL,
	name varchar2(100) NOT null,
	roomname varchar2(100) NOT NULL,-- 업체방 테이블의 roomidx와 연결
	--email varchar2(100) NOT NULL,
	total  number NOT NULL,	            -- 예약 인원수
	startday varchar2(100) NOT NULL,
	endday varchar2(100) NOT NULL
);

-- 광고 테이블
CREATE SEQUENCE AD_idx_seq;
CREATE TABLE AD(
	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL,			-- 회원 및 관리자 계정 테이블의 id와 연결
	subject varchar2(500) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	col1 varchar2(100),
	col2 NUMBER
);

-- 게시판 테이블
CREATE SEQUENCE fileBoard_idx_seq;
CREATE TABLE fileBoard(
 	idx NUMBER PRIMARY KEY,
	id varchar2(100) NOT NULL, 			-- 회원 및 관리자 계정 테이블의 id와 연결
	subject varchar2(500) NOT NULL,
	content varchar2(4000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	clickCount NUMBER(20) DEFAULT 0
);

DROP TABLE fileboard;
DROP SEQUENCE fileBoard_idx_seq;

INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject', 'testcontent', sysdate, 0
);
INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject2', 'testcontent2', sysdate, 0
);
INSERT INTO JSPUSER.FILEBOARD
(IDX, ID, SUBJECT, CONTENT, REGDATE, CLICKCOUNT)
VALUES(fileBoard_idx_seq.nextval, 'admin', 'testsubject3', 'testcontent3', sysdate, 0
);


COMMIT;

SELECT * FROM TAB;
DROP SEQUENCE member_idx_seq;
DROP TABLE MEMBERS ;
SELECT * FROM MEMBER;
SELECT * FROM COMPANY_ROOM;
SELECT * FROM COMPANY;
select * from company eco in(SELECT eco FROM COMPANY c WHERE areacode=3 and detailcode=301);
SELECT * FROM company WHERE '산' in (SELECT eco FROM COMPANY c WHERE areacode=3 and detailcode=301); -- 코드값으로 먼저 분류한 업체들
-- 윗 리스트에서 선택한 값이 있는걸 가져와야 한다.
-- 그렇다면 
SELECT eco FROM COMPANY c WHERE areacode=3 and detailcode=301 AND eco = '강';

--전체 업체(지역코드 3 상제주소 303 )
select
c.name 
from company c 
WHERE c.AREACODE =3 AND DETAILCODE =303
AND c.NAME LIKE '%캠핑%';

select * from (select rownum rnum, R.* from (select c.* from company c WHERE areacode=3 and 
detailcode=304 order by c.idx DESC) R where rownum <= 20 ) Q where rnum >= 11; 

SELECT LATITUDE , LONGITUDE , NAME  FROM COMPANY c; 


