package kr.human.camping.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.Admin_CompanyRoomDAO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Service("Admin_CompanyRoomService")
@Transactional
@Slf4j
public class Admin_CompanyRoomServiceImpl  implements Admin_CompanyRoomService{

	@Autowired
	private Admin_CompanyRoomDAO admin_CompanyRoomDAO;

	@Override
	public List<RoomVO> selectRoomList(int idx) {
		List<RoomVO> list = null;
		try {
			list = admin_CompanyRoomDAO.selectCompanyRoomList(idx);
			log.info("selectRoomList 가져오기 : " + list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RoomVO selectByRoomIdx(int roomidx) {
		RoomVO vo = null;
		try {
			vo = admin_CompanyRoomDAO.selectByRoomIdx(roomidx);
			log.info("selectByRoomIdx 가져오기 : " + vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insert(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				admin_CompanyRoomDAO.insert(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				admin_CompanyRoomDAO.update(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public boolean delete(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				admin_CompanyRoomDAO.delete(roomVO.getRoomidx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
