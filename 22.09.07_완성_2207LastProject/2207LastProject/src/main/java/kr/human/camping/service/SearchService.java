package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchPagingVO;

public interface SearchService {
	//1.지역코드만 검색한 경우
	List<CompanyVO> CompanyAreaCode(int areacode);
	
	//2.지역코드, 상세코드 , 각 테마 검색한경우
	SearchPagingVO<CompanyVO> CompanyCode(int currentPage, int pageSize, int blockSize,int areacode, int detailcode, List<String> eco,List<String>roomtype,List<String> theme, String keyword);
	
	//3.전체 업체 가져오기 
	List<CompanyVO> totalCompany();
}
