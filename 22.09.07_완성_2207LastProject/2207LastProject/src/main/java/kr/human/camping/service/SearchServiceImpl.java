package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.SearchDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchPagingVO;

@Service("serchService")
@Transactional
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDAO searchDAO;

	@Override
	public List<CompanyVO> CompanyAreaCode(int areacode) {
		List<CompanyVO> companyVO = null;
		try {
			companyVO = searchDAO.searchByareacode(areacode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return companyVO;
	}

	@Override
	public SearchPagingVO<CompanyVO> CompanyCode(int currentPage, int pageSize, int blockSize,int areacode, int detailcode,List<String> eco,List<String> roomtype,List<String> theme,String keyword) {
		SearchPagingVO<CompanyVO> pagingVO = null;
		Map<String, Object> vomap = new HashMap<>();

		try {
			System.out.println("CompanyCode 호출 ");
			System.out.println("*".repeat(50));
			System.out.println("SearchServiceImpl eco데이터 확인 : " + eco);
			System.out.println("*".repeat(50));
			System.out.println("SearchServiceImpl roomtype데이터 확인 : " + roomtype);
			System.out.println("*".repeat(50));
			System.out.println("SearchServiceImpl theme데이터 확인 : " + theme);
			System.out.println("*".repeat(50));
			System.out.println("SearchServiceImpl keyowrd데이터 확인 : "+ keyword);
			System.out.println("*".repeat(50));
			
			vomap.put("areacode", areacode);
			vomap.put("detailcode", detailcode);
			if(keyword !=null) { //keyword가 null 아니고 ""이게 아닐때 
				System.out.println("null이면 실행 안됩니다.-------------------------------------------");
				System.out.println("serviceImpl에서의 null 체크 후 keyword : " + keyword);
				System.out.println("-------------------------------------------------------");
				
				vomap.put("keyword", keyword);
			}
			if(eco !=null) { // eco null아니고 
				System.out.println("null이면 실행 안됩니다.---------------------------------------");
				System.out.println("serviceImpl에서의 null 체크 후 eco : "+eco);
				System.out.println("-------------------------------------------------------");
				vomap.put("eco", eco);
				
			}
			if(roomtype !=null ) {
				System.out.println("null이면 실행 안됩니다.---------------------------------------");
				System.out.println("serviceImpl에서의 null 체크 후 roomtype : "+roomtype);
				System.out.println("-------------------------------------------------------");
				vomap.put("roomtype", roomtype);
			}
			if(theme !=null ) {
				System.out.println("null이면 실행 안됩니다.---------------------------------------");
				System.out.println("serviceImpl에서의 null 체크 후 theme : "+theme);
				System.out.println("-------------------------------------------------------");
				vomap.put("theme", theme);
			}
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(vomap);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			
			int totalCount = searchDAO.searchCount(vomap);
			System.out.println("serviceImpl: " + vomap);
			System.out.println("serviceImpl: " + totalCount + "개");
			
			pagingVO = new SearchPagingVO<>(totalCount, currentPage, pageSize, blockSize,areacode,detailcode,eco,roomtype,theme,keyword);
			vomap.put("startNo", pagingVO.getStartNo());
			vomap.put("endNo", pagingVO.getEndNo());
			
			System.out.println("ServiceImpl에서의 CompanyCode(pagingmap)호출 :  " + vomap);
			System.out.println("----------------------------------------------------------");
			System.out.println("ServiceImpl에서의 CompanyCode(pagingVO)호출 :  " + pagingVO);
			if(pagingVO.getTotalCount()>0)
				pagingVO.setList(searchDAO.searchBycode(vomap));
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("ServiceImpl에서의 리턴 되기전 CompanyCode(pagingVO)호출 :  " + pagingVO);
		return pagingVO;
	}

	@Override
	public List<CompanyVO> totalCompany() {
		List<CompanyVO> totalCompanyVO = null;
		 try {
			totalCompanyVO = searchDAO.totalCompany();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCompanyVO;
	}

	
	
	
}
