package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.CompanyDAO;
import kr.human.camping.dao.ReservationDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.ReservationVO;
import kr.human.camping.vo.RoomVO;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO reservationDAO;

	@Override
	public PagingVO<ReservationVO> selectReservationList(int currentPage, int pageSize, int blockSize) {
		PagingVO<ReservationVO> pagingVO = null;
		try {
			int totalCount = reservationDAO.selectReservationCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<ReservationVO> list = reservationDAO.selectReservationList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public List<ReservationVO> selectMyReservation(String id) {
		List<ReservationVO> list = null;
		try {
			list = reservationDAO.selectMyReservation(id);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ReservationVO selectReservation(int roomidx) {
		ReservationVO vo = null;
		try {
			vo = reservationDAO.selectReservation(roomidx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insertReservation(ReservationVO reservationVO) {
		boolean result = false;
		if(reservationVO!=null) {
			try {
				reservationDAO.insertReservation(reservationVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteReservation(ReservationVO reservationVO) {
		boolean result = false;
		if(reservationVO!=null) {
			try {
				reservationDAO.deleteReservation(reservationVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public CompanyVO selectCompany(int roomidx) {
		CompanyVO vo = null;
		try {
			vo = reservationDAO.selectByIdx(roomidx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<ReservationVO> selectReservationRoomList(int idx) {
		List<ReservationVO> list = null;
		
		try {
			list = reservationDAO.selectReservationRoomList(idx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
