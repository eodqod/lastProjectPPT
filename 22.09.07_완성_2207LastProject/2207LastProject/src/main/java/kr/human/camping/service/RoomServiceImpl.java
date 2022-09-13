package kr.human.camping.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.RoomDAO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("roomService")
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	public List<RoomVO> selectRoomList(int idx) {
		List<RoomVO> list = null;
		try {
			list = roomDAO.selectRoomList(idx);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public RoomVO selectRoom(int roomIdx) {
		RoomVO vo = null;
		try {
			vo = roomDAO.selectRoom(roomIdx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insertRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.insertRoom(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean updateRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.updatetRoom(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.deletetRoom(roomVO.getRoomidx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int companyidx(int roomidx) {
		int a = 0;
		try {
			a = roomDAO.companyidx(roomidx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}


}
