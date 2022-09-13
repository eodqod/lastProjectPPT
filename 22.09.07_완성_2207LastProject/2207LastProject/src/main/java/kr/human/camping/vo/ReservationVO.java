package kr.human.camping.vo;

import lombok.Data;

@Data
public class ReservationVO {
	private String id;
	private int roomidx;
	private String name;
	private String roomname;
//	private String email;
	private int total;
	private String startday;
	private String endday;
}
