package kr.human.camping.service;

import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.SearchListPagingVO;
import kr.human.camping.vo.TestVO;

public interface TestService {
	String today();
	int    sum(int num1, int num2);
	int    mul(int num1, int num2);
	TestVO vo(int num1, int num2);
//	SelectRolePagingVO<MemberVO> selectByMemberList(String role, int p, int s, int b);
	// 1개 내용보기
	FileBoardVO selectByIdx(int idx, boolean isClick); // isClick 조회수 때문에
	// 전체 회원 목록보기
	SearchListPagingVO<FileBoardVO> selectList(String keyword, int p, int s, int b);
}

