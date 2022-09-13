package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.FileBoardDAO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchListPagingVO;
import lombok.extern.slf4j.Slf4j;

@Service("fileBoardService")
@Transactional
@Slf4j
public class FileBoardServiceImpl  implements FileBoardService{

	@Autowired
	private FileBoardDAO fileBoardDAO;

	@Override
	public SearchListPagingVO<FileBoardVO> selectList(String keyword, int p, int s, int b) {
		SearchListPagingVO<FileBoardVO> pagingVO = null;
		HashMap<String, Object> map = new HashMap<>();
		try {
			if(keyword != null) {
				map.put("keyword", keyword);
			}
			int totalCount = fileBoardDAO.selectCount(map);
			log.info("testservice map 호출 : " + map);
			log.info("testservice totalCount 호출 : " + totalCount);
			pagingVO = new SearchListPagingVO<>(totalCount, p, s, b, keyword);
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("keyword", keyword);
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<FileBoardVO> list = fileBoardDAO.selectList(hashMap);
			log.info("testservice hashMap 호출 : " + hashMap);
			log.info("testservice list 호출 : " + list);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}
	
	@Override
	public FileBoardVO selectByIdx(int idx, boolean isClick) {
		FileBoardVO fileBoardVO= null;
		//------------------------------------------------------------

		try {
			//--------------------------------------------------------------------
			// 1. 해당 글번호의 글을 가져온다.
			fileBoardVO = fileBoardDAO.selectByIdx(idx);

			if(fileBoardVO!=null && isClick) { // 글이 존재하면서 isClick이 참이면 조회수 증가
				fileBoardVO.setClickCount(fileBoardVO.getClickCount()+1); // 나의 조회수 증가
//				fileBoardDAO.increment(sqlSession, idx); // DB의 조회수 증가
			}
			//--------------------------------------------------------------------
		}catch (Exception e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------------------------------
		return fileBoardVO;
	}
	

	@Override
	public boolean insert(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				fileBoardDAO.insert(fileBoarVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoarVO.getIdx()); // DB에서 원본 가져오기
				fileBoardDAO.update(fileBoarVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoarVO.getIdx()); // DB에서 원본 가져오기
				fileBoardDAO.delete(fileBoarVO.getIdx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


}
