package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.CompanyDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDAO companyDAO;
	
	@Override
	public PagingVO<CompanyVO> CompanyList(int currentPage, int pageSize, int blockSize) {
		PagingVO<CompanyVO> pagingVO = null;
		try {
			int totalCount = companyDAO.selectCompanyCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("startNo",  Integer.toString(pagingVO.getStartNo()));
			hashMap.put("pageSize", Integer.toString(pagingVO.getPageSize()));
			List<CompanyVO> list = companyDAO.selectCompanyList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public CompanyVO selectCompany(int idx) {
		CompanyVO vo = null;
		try {
			vo = companyDAO.selectById(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}


	@Override
	public boolean insertCompany(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				companyDAO.insert(companyVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean updateCompany(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				companyDAO.update(companyVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
 
	@Override
	public boolean deleteCompany(CompanyVO companyVO) {
		boolean result = false;
		if(companyVO!=null) {
			try {
				companyDAO.delete(companyVO.getIdx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
