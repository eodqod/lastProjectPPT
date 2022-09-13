package kr.human.camping.service;

import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchListPagingVO;

public interface FileBoardService {
	// 전체 회원 목록보기
	SearchListPagingVO<FileBoardVO> selectList(String keyword, int p, int s, int b);
	// 1개 내용보기
	FileBoardVO selectByIdx(int idx, boolean isClick); // isClick 조회수 때문에
	// 저장하기
	boolean insert(FileBoardVO fileBoardVO);
	// 수정하기
	boolean update(FileBoardVO fileBoardVO);
	// 삭제하기
	boolean delete(FileBoardVO fileBoardVO);
}
