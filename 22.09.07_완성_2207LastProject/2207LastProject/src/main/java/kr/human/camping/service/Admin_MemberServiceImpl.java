package kr.human.camping.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.Admin_MemberDAO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SelectRolePagingVO;
import lombok.extern.slf4j.Slf4j;

@Service("Admin_MemberService")
@Transactional
@Slf4j
public class Admin_MemberServiceImpl implements Admin_MemberService{

	@Autowired
	private Admin_MemberDAO memberDAO;

	@Override
	public SelectRolePagingVO<MemberVO> selectByMemberList(String role, int p, int s, int b) {
		SelectRolePagingVO<MemberVO> pagingVO = null;
		HashMap<String, Object> vomap = new HashMap<>();
		try {
			vomap.put("role", role);
			int totalCount = memberDAO.selectCount(vomap);
			log.info("testservice vomap 호출 : " + vomap);
			log.info("testservice totalCount 호출 : " + totalCount);
			pagingVO = new SelectRolePagingVO<>(totalCount, p, s, b, role);
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("role", role);
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<MemberVO> list = memberDAO.selectByMemberList(hashMap);
			log.info("testservice hashMap 호출 : " + hashMap);
			log.info("testservice list 호출 : " + list);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

}
