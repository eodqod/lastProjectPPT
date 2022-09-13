package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.RoomVO;

public interface RoomService {
	// 해당 회사의 방 한페이지 구하기
	List<RoomVO> selectRoomList(int idx);
	// 방 1개 보기
	RoomVO selectRoom(int roomIdx);
	// 방 추가하기
	boolean insertRoom(RoomVO roomVO);
	// 방 수정하기
	boolean updateRoom(RoomVO roomVO);
	// 방 삭제하기
	boolean deleteRoom(RoomVO roomVO);
	// 해당 방의 업체 아이디 가져오기
	int companyidx(int roomidx);
}
