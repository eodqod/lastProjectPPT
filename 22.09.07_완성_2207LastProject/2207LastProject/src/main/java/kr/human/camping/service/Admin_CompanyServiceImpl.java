package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.Admin_CompanyDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service("Admin_CompanyService")
@Transactional
@Slf4j
public class Admin_CompanyServiceImpl  implements Admin_CompanyService{

	@Autowired
	private Admin_CompanyDAO admin_CompanyDAO;

	@Override
	public PagingVO<CompanyVO> selectList(int currentPage, int pageSize, int blockSize) {
		PagingVO<CompanyVO> pagingVO = null;
		try {
			int totalCount = admin_CompanyDAO.selectCompanyCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<CompanyVO> list = admin_CompanyDAO.selectList(hashMap);
			log.info("쿼리결과 : {}",list.toString());
			System.out.println("----------------------------------------------------------");
			System.out.println("Admin_CompanyServiceImpl에서의 selectList(pagingVO)호출 :  " + pagingVO);
			System.out.println("----------------------------------------------------------");
			System.out.println("Admin_CompanyServiceImpl에서의 selectList(list)호출 :  " + list);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Admin_CompanyServiceImpl에서의 리턴전selectList(pagingVO)호출 :  " + pagingVO);
		return pagingVO;
	}

	@Override
	public CompanyVO selectByIdx(int idx) {
		CompanyVO companyVO= null;
		try {
			companyVO = admin_CompanyDAO.selectByIdx(idx);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return companyVO;
	}

	@Override
	public boolean insert(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				admin_CompanyDAO.insert(companyVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				CompanyVO dbVO = admin_CompanyDAO.selectByIdx(companyVO.getIdx()); // DB에서 원본 가져오기
				admin_CompanyDAO.update(companyVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				CompanyVO dbVO = admin_CompanyDAO.selectByIdx(companyVO.getIdx()); // DB에서 원본 가져오기
				admin_CompanyDAO.delete(companyVO.getIdx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
