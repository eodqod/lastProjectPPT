package kr.human.camping.vo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchVO {
	//현재 페이지 번호
	private int currentPageNo;
	//페이지당 출력할 데이터 개수
	private int recordsPerPage;
	//화면하단에 출력할 페이지 사이즈
	private int pageSize;
	//검색키워드
	private String searchType;
	
	public SearchVO() {
		
		this.currentPageNo =1;
		this.recordsPerPage = 10;
		this.pageSize=10;
	}
	
	public int getStartPage() {
		return (currentPageNo-1 ) * recordsPerPage;
	}
}
