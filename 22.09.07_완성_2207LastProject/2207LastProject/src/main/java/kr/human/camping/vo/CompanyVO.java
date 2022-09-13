package kr.human.camping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyVO {
	private int idx;
	private String name;
	private String add1;
	private String add2;
	private int postcode;
	private String eco;
	private String roomtype;
	private String theme;
	private int areacode;
	private int detailcode;
	private double latitude; //위도
	private double longitude; //경도
	private String col1;
	private int col2;
}
