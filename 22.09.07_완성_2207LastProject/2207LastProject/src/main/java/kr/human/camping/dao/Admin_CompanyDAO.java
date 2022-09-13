package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.CompanyVO;

@Mapper
public interface Admin_CompanyDAO {
	// 전체 업체 갯수
	int selectCompanyCount() throws SQLException;
	// 1개 가져오기
	CompanyVO selectByIdx(int idx) throws SQLException;
	// 전체목록 가져오기
	List<CompanyVO> selectList(HashMap<String, Integer> map)throws SQLException;
	// 업체 등록
	void insert(CompanyVO companyVO) throws SQLException;
	// 업체 수정
	void update(CompanyVO companyVO) throws SQLException;
	// 업체 삭제
	void delete(int idx) throws SQLException;
}
