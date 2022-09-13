package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.MemberVO;

@Mapper
public interface Admin_MemberDAO {
	// 회원 전체 인원수
	int selectCount(HashMap<String, Object> vomap) throws SQLException;
	// 전체 회원 목록 가져오기
	List<MemberVO> selectByMemberList(HashMap<String, Object> hashMap) throws SQLException;
}
