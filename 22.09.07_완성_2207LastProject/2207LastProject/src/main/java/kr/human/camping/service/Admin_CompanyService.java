package kr.human.camping.service;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;

public interface Admin_CompanyService {
	// 목록보기
	PagingVO<CompanyVO> selectList(int currentPage, int pageSize, int blockSize);
	// 1개 내용보기
	CompanyVO selectByIdx(int idx);
	// 저장하기
	boolean insert(CompanyVO companyVO);
	// 수정하기
	boolean update(CompanyVO companyVO);
	// 삭제하기
	boolean delete(CompanyVO companyVO);
}
