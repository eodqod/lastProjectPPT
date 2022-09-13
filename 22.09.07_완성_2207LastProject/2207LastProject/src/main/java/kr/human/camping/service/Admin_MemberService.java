package kr.human.camping.service;

import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SelectRolePagingVO;

public interface Admin_MemberService {
	// 전체 회원 목록보기
	SelectRolePagingVO<MemberVO> selectByMemberList(String role, int p, int s, int b);
}
