package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.ReservationVO;

@Mapper
public interface ReservationDAO {
	// 전체 개수 구하기
	int selectReservationCount() throws SQLException;
	// 예약 1개 보기
	ReservationVO selectReservation(int roomidx) throws SQLException;
	// 나의 예약목록 보기
	List<ReservationVO> selectMyReservation(String id) throws SQLException;
	// 예약하기
	void insertReservation(ReservationVO reservationVO) throws SQLException;
	// 예약 취소하기
	void deleteReservation(ReservationVO reservationVO) throws SQLException;
    // 한 페이지 정보 보기
	List<ReservationVO> selectReservationList(HashMap<String, Integer> map) throws SQLException;
	
	
	
	List<ReservationVO> selectReservationRoomList(int roomidx) throws SQLException;
	// 업체 한개 가져오기(예약시)
	CompanyVO selectByIdx(int roomidx) throws SQLException;
	
}
