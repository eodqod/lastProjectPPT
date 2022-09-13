package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.TestVO;

@Mapper
public interface TestDAO {
	String today() throws SQLException;
	int    sum(HashMap<String, Integer> map) throws SQLException;
	int    mul(HashMap<String, Integer> map) throws SQLException;
	TestVO vo(TestVO testVO) throws SQLException;
	
	// 전체 개수
	int selectCount(HashMap<String, Object> map) throws SQLException;
	// 1개 가져오기
	FileBoardVO selectByIdx(int idx) throws SQLException;
	// 전체목록 가져오기
	List<FileBoardVO> selectList(HashMap<String, Object> map) throws SQLException;
}
