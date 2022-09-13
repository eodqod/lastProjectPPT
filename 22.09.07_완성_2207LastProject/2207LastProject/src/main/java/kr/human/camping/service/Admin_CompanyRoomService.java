package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.RoomVO;

public interface Admin_CompanyRoomService {
	// 업체당 방 목록 가져오기
	List<RoomVO> selectRoomList(int idx);
	// 방 1개 내용보기
	RoomVO selectByRoomIdx(int roomidx);
	// 저장하기
	boolean insert(RoomVO roomVO);
	// 수정하기
	boolean update(RoomVO roomVO);
	// 삭제하기
	boolean delete(RoomVO roomVO);

}
