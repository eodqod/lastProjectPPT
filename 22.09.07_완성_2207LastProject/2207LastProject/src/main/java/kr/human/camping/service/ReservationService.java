package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.ReservationVO;

public interface ReservationService {
	// 전체 목록보기(관리자용)
	PagingVO<ReservationVO> selectReservationList(int currentPage, int pageSize, int blockSize);
	// 나의 예약 목록 보기(회원용)
	List<ReservationVO> selectMyReservation(String id);
	
	List<ReservationVO> selectReservationRoomList(int idx);
	// 예약 1개 보기
	ReservationVO selectReservation(int roonidx);
	// 업체 1개 보기
	CompanyVO selectCompany(int idx);
	// 예약하기
	boolean insertReservation(ReservationVO reservationVO);
	// 예약 취소하기
	boolean deleteReservation(ReservationVO reservationVO);
}
