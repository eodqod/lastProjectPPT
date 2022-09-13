package kr.human.camping.vo;

import java.util.List;

public class SearchPagingVO<T> {

	private int totalCount;
	private int currentPage;
	private int pageSize;
	private int blockSize;

	private int totalPage;
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;
	
	private int areacode;
	private int detailcode;
	List<String> eco;
	List<String> roomtype;
	List<String> theme;
	String keyword;
	private List<T> list;
	
	public SearchPagingVO(int totalCount, int currentPage, int pageSize, int blockSize,int areacode, int detailcode, List<String> eco,List<String>roomtype,List<String> theme,String keyword) {
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.areacode = areacode;
		this.detailcode = detailcode;
		this.eco =eco;
		this.roomtype=roomtype;
		this.theme=theme;
		this.keyword=keyword;
		calc();
	}

	private void calc() {
		if(currentPage<1) currentPage = 1;
		if(pageSize<2) pageSize = 10;
		if(blockSize<2) blockSize = 10;
		
		if(totalCount>0) {
			totalPage = (totalCount-1)/pageSize + 1;
			if(currentPage>totalPage) currentPage = 1;
			
			startNo = (currentPage-1) * pageSize + 1;
			endNo   = startNo + pageSize - 1;
			if(endNo>totalCount) {
				endNo = totalCount;
			}
			
			startPage = (currentPage-1)/blockSize * blockSize + 1;
			endPage = startPage + blockSize - 1;
			if(endPage>totalPage) {
				endPage = totalPage;
			}
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	

	// 메서드 2개 추가하자 : 상단의 페이지 정보, 하단의 페이지 리스트
	// 상단의 페이지 정보
	
	public String getPageInfo() {
		String result = "전체 : " + totalCount + "개";
		if(totalCount>0) {
			result += "(" + currentPage + "/" + totalPage + "Page)";
		}
		return result;
	}
	// 하단의 페이지 리스트
	public String getPageList() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class='pagination pagination-sm justify-content-center'>");
		
		
			// 이전
			if(startPage>1) {
					sb.append("<li class='page-item'>");
					sb.append("<a class='page-link' href='#' aria-label='Previous' onclick='sendGet(\"?\", {\"p\":"+(startPage-1)+",\"s\":"+pageSize+",\"b\":"+blockSize+",\"area1\":"+areacode+",\"area2\":"+detailcode+",\"eco\":"+eco+",\"roomtype\":"+roomtype+",\"keyword\":"+keyword+",\"theme\":"+theme+"})'>");
					sb.append("<span aria-hidden='true'>&laquo;</span>");
					sb.append("</a>");
					sb.append("</li>");
			}
			// 페이지 리스트
			for(int i=startPage;i<=endPage;i++) {
				// 현재 페이지는 링크를 걸지 않는다.
				if(i==currentPage) {
					sb.append("<li class='page-item active' aria-current='page'>");
					sb.append("<a class='page-link' href='#'>");
					sb.append(i);
					sb.append("</a>");
					sb.append("</li>");				
				}else {
					sb.append("<li class='page-item'>");
					sb.append("<a class='page-link' href='#' id='page-link' onclick='sendGet(\"?\", {\"p\":" + (i) + ",\"s\":"+pageSize+",\"b\":"+blockSize+",\"area1\":"+areacode+",\"area2\":"+detailcode+",\"eco\":"+eco+",\"roomtype\":"+roomtype+",\"keyword\":"+keyword+",\"theme\":"+theme+"})'>");
					sb.append(i);
					sb.append("</a>");
					sb.append("</li>");				
				}
			}
			// 다음
			if(endPage<totalPage) {
					sb.append("<li class='page-item'>");
					sb.append("<a class='page-link' href='#' aria-label='Next' onclick='sendGet(\"?\", {\"p\":"+(endPage+1)+",\"s\":"+pageSize+",\"b\":"+blockSize+",\"area1\":"+areacode+",\"area2\":"+detailcode+",\"eco\":"+eco+",\"roomtype\":"+roomtype+",\"keyword\":"+keyword+",\"theme\":"+theme+"})'>");
					sb.append("<span aria-hidden='true'>&raquo;</span>");
					sb.append("</a>");
					sb.append("</li>");
			}
			sb.append("</ul>");
			return sb.toString();
		
	}
	@Override
	public String toString() {
		return "PagingVO [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", blockSize=" + blockSize + ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo=" + endNo
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", list=" + list + "]";
	}
	
	/*
	   public String getPageInfo() {
	      String result = "전체 : " + totalCount + "개";
	      if(totalCount>0) {
	         result += "(" + currentPage + "/" + totalPage + "Page)";
	      }
	      return result;
	   }
	   // 하단의 페이지 리스트
	   public String getPageList() {
	      StringBuffer sb = new StringBuffer();
	      sb.append("<ul class='pagination pagination-sm justify-content-center'>");
	      
	      
	         // 이전
	         if(startPage>1) {
	               sb.append("<li class='page-item'>");	               
	               sb.append("<a class='page-link' aria-label='Previous' href='?p="+(startPage-1)+"&s="+pageSize+"&b="+blockSize+"&area1="+areacode+"&area2="+detailcode+"&eco="+eco+"&roomtype="+roomtype+"&theme="+theme+"&keyword="+keyword+"'>");
	               sb.append("<span aria-hidden='true'>&laquo;</span>");
	               sb.append("</a>");
	               sb.append("</li>");
	         }
	         // 페이지 리스트
	         for(int i=startPage;i<=endPage;i++) {
	            // 현재 페이지는 링크를 걸지 않는다.
	            if(i==currentPage) {
	               sb.append("<li class='page-item active' aria-current='page'>");
	               sb.append("<a class='page-link' href='#'>");
	               sb.append(i);
	               sb.append("</a>");
	               sb.append("</li>");            
	            }else {
	               sb.append("<li class='page-item'>");
	               sb.append("<a class='page-link' href='?p=" + (i) + "&s="+pageSize+"&b="+blockSize+"&area1="+areacode+"&area2="+detailcode+"&eco="+eco+"&roomtype="+roomtype+"&theme="+theme+"&keyword="+keyword+"'>");
	               sb.append(i);
	               sb.append("</a>");
	               sb.append("</li>");            
	            }
	         }
	         // 다음
	         if(endPage<totalPage) {
	               sb.append("<li class='page-item'>");
	               sb.append("<a class='page-link' aria-label='Next' href='?p="+(endPage+1)+ "&s="+pageSize+"&b="+blockSize+"&area1="+areacode+"&area2="+detailcode+"&eco="+eco+"&roomtype="+roomtype+"&theme="+theme+"&keyword="+keyword+"'>");
	               sb.append("<span aria-hidden='true'>&raquo;</span>");
	               sb.append("</a>");
	               sb.append("</li>");
	         }
	         sb.append("</ul>");
	         return sb.toString();
	      
	   }

	   @Override
	   public String toString() {
	      return "PagingVO [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageSize=" + pageSize
	            + ", blockSize=" + blockSize + ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo=" + endNo
	            + ", startPage=" + startPage + ", endPage=" + endPage + ", list=" + list + "]";
	   }
	   */
	
}
