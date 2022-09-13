package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.RoomVO;

@Mapper
public interface Admin_CompanyRoomDAO {
	// 업체당 방 목록 가져오기
	List<RoomVO> selectCompanyRoomList(int idx) throws SQLException;
	// 업체 방 1개 가져오기
	RoomVO selectByRoomIdx(int roomidx) throws SQLException;
	// 업체 방 등록
	void insert(RoomVO roomVO) throws SQLException;
	// 업체 방 수정
	void update(RoomVO roomVO) throws SQLException;
	// 업체 방 삭제
	void delete(int idx) throws SQLException;
}
