package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.MemberVO;

@Mapper
public interface MemberDAO {

	// 1. 회원 가입
	void insert(MemberVO vo) throws SQLException;
	// 2. 회원 정보 수정
	void update(MemberVO vo) throws SQLException;
	// 3. 회원 탈퇴
	void delete(MemberVO vo) throws SQLException;
	// 4. 회원 정보 가져오기
	MemberVO selectByMemberInfo(String id) throws SQLException;
	// 5. 전체 회원 리스트 가져오기
	List<MemberVO> selectByMemberInfoList() throws SQLException;
	// 6. 사용자 권한 확인하기
	String checkAccess(MemberVO vo) throws SQLException;
	// 7. 사용자 권한 주기
	void insertAccess(MemberVO vo) throws SQLException;
	// 8. 로그인
	int login(HashMap<String, String> map) throws SQLException;
	// 9. id중복 검사
	int IDOverlap(String id) throws SQLException;
	// 10. email중복 검사
	int EmailOverlap(String email) throws SQLException;
	// 11. id찾기
	MemberVO findID(String email) throws SQLException;
	// 12. password바꾸기
	void changePassword(HashMap<String, String> map) throws SQLException;
	// 13. 권한테이블 회원정보 삭제
	void deleteAccess(String id) throws SQLException;
	// 14. 권한'user'로 바꿔주기
	void updateAccess(String id) throws SQLException;
	
	
}
