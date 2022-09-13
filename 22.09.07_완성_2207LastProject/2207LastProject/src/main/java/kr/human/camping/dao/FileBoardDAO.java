package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.FileBoardVO;

@Mapper
public interface FileBoardDAO {
	// 전체 개수
	int selectCount(HashMap<String, Object> map) throws SQLException;
	// 1개 가져오기
	FileBoardVO selectByIdx(int idx) throws SQLException;
	// 전체목록 가져오기
	List<FileBoardVO> selectList(HashMap<String, Object> map) throws SQLException;
	// 저장
	void insert(FileBoardVO fileBoardVO) throws SQLException;
	// 수정
	void update(FileBoardVO fileBoardVO) throws SQLException;
	// 삭제
	void delete(int idx) throws SQLException;
	// 조회수
	void clickCount(int idx) throws SQLException;
}
