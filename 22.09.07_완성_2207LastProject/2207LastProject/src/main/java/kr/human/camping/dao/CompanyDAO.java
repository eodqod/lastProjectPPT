package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.CompanyVO;

@Mapper
public interface CompanyDAO {
	// 전체 업체수 구하기
	int selectCompanyCount() throws SQLException;
	// 업체 한개 가져오기(예약시)
	CompanyVO selectById(int idx) throws SQLException;
	// 업체 저장하기
	void insert(CompanyVO companyVO) throws SQLException;
	// 업체 수정하기
	void update(CompanyVO companyVO) throws SQLException;
	// 업체 삭제하기
	void delete(int idx) throws SQLException;
	// 업체 한 페이지 가져오기
	List<CompanyVO> selectCompanyList(HashMap<String, String> map) throws SQLException;
}
