package kr.human.camping.service;



import java.util.HashMap;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.human.camping.vo.MemberVO;

public interface MemberService {
	
	// 1. 회원정보 저장
	void MemberInsert(MemberVO vo);
	// 2. 회원정보 수정 처리
	void MemberUpdate(MemberVO vo);
	// 2-1. 로그인된  회원의 정보 가져오기
	MemberVO MeberInfo(String id);
	// 3. 회원 탈퇴 처리
	void MemberDelete(MemberVO vo);
	// 4. 로그인 처리
	boolean Login(String id, String password);
	// 5. ID중복 확인
	int IDOverlap(String id);
	// 6. Email중복 확인
	int EmailOverlap(String email);
	// 7. password 변경
	void changePassword(HashMap<String, String> map);
	// 8. ID 찾기
	HashMap<String, String> findID(String email);
	// 9. 비밀번호 비교
	boolean passwordcheck(String password, MemberVO vo);
	// 10. 메일인증후 권한 'user'로 변환해주기
	void updateAccess(String id);
	
}
