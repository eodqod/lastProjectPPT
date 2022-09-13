package kr.human.camping.service;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;

public interface CompanyService {
	// 목록보기
	PagingVO<CompanyVO> CompanyList(int currentPage, int pageSize, int blockSize);
	
	// 1개 보기
	CompanyVO selectCompany(int idx);
	
	// 저장하기
	boolean insertCompany(CompanyVO companyVO);
	
	// 수정하기
	boolean updateCompany(CompanyVO companyVO);
	
	// 삭제하기
	boolean deleteCompany(CompanyVO  companyVO);
	
}
