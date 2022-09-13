package kr.human.camping.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.SearchService;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.SearchPagingVO;

@Controller
public class SearchController {
	

	@Autowired
	private SearchService searchService;
	
	//ajax 주소받아오기
	@RequestMapping(value="/mapList" , method = RequestMethod.GET)
	@ResponseBody
	public List<CompanyVO> mapList() {
		List<CompanyVO> totalCompany = searchService.totalCompany();
		return totalCompany;
	}
	//map 테스트용
	@RequestMapping(value="/map" , method = RequestMethod.GET)
	public String map() {
		return "map";
	}
	
	
	@RequestMapping(value="/search" , method = RequestMethod.GET)
	public String search(
			@RequestParam(required = false, defaultValue = "1") Integer area1,
			@RequestParam(required = false, defaultValue = "101") Integer area2,
			@RequestParam(required = false) List<String> eco,
			@RequestParam(required = false) List<String> roomtype,
			@RequestParam(required = false) List<String> theme,
			@RequestParam(required = false, defaultValue = "1") int p,  //현재 페이지
			@RequestParam(required = false, defaultValue = "10") int s, //보여줄 리스트 갯수
			@RequestParam(required = false, defaultValue = "5") int b,  //네비게이션 바의 갯수
			@RequestParam(required = false) String keyword,
			Model model) {
		

			
			System.out.println("데이터 확인!!" + "area1 : " + area1 + "," +"area2 : "+ area2 + "," +"eco : "+ eco + "," +"roomtype : "+roomtype+ ","+"theme : " +theme+","+"keyword : "+keyword);
			System.out.println("-".repeat(50));
			System.out.println("SearchController eco데이터 확인 : " + eco);

			System.out.println("-".repeat(50));
			System.out.println("SearchController roomtype데이터 확인 : " + roomtype);

			System.out.println("-".repeat(50));
			System.out.println("SearchController theme데이터 확인 : " + theme);

			System.out.println("-".repeat(50));
			System.out.println("SearchController keyowrd데이터 확인 : "+ keyword);
			System.out.println("-".repeat(50));
			
			if(eco!=null) {
				System.out.println("*".repeat(80));
				System.out.println(eco.size());
				System.out.println("*".repeat(80));
				if(eco.size()==0) eco = null;
			}
			if(roomtype!=null) {
				System.out.println("*".repeat(80));
				System.out.println(roomtype.size());
				System.out.println("*".repeat(80));
				if(roomtype.size()==0) roomtype = null;
			}
			if(theme!=null) {
				System.out.println("*".repeat(80));
				System.out.println(theme.size());
				System.out.println("*".repeat(80));
				if(theme.size()==0) theme = null;
			}
		
			

			
			SearchPagingVO<CompanyVO> pv = searchService.CompanyCode(p, s, b,area1, area2,eco,roomtype,theme,keyword);
			List<CompanyVO> totalCompany = searchService.totalCompany();
			model.addAttribute("pv",pv);
			model.addAttribute("totalCompany",totalCompany);
			model.addAttribute("p", p);
			model.addAttribute("s", s);
			model.addAttribute("b", b);
			model.addAttribute("br", "<br>");
			model.addAttribute("newLine", "\n");		
			model.addAttribute("area1",area1);
			model.addAttribute("area2",area2);
			
			if(eco !=null) {
				String e = eco.toString();
				e = e.substring(1, e.length()-1);
				model.addAttribute("eco",e);
			}else {
			model.addAttribute("eco",eco);
			}
			if(roomtype != null) {
				String r = roomtype.toString();
				r = r.substring(1, r.length()-1);
				model.addAttribute("roomtype",r);
			}else {
			model.addAttribute("roomtype",roomtype);
			}
			if(theme != null) {
				String t = theme.toString();
				t = t.substring(1, t.length()-1);
				model.addAttribute("theme",t);
			}else {
			model.addAttribute("roomtype",theme);
			}
			
			model.addAttribute("eco_list",eco_list());
			model.addAttribute("roomtype_list",roomtype_list());
			model.addAttribute("theme_list",theme_list());
			model.addAttribute("keyword",keyword);
		return "search";
	}
	@ModelAttribute("eco_list")
	public Map<String, String> eco_list(){
		Map<String,String> eco_list= new LinkedHashMap<String, String>();
		eco_list.put("mountain", "산");
		eco_list.put("sea", "바다");
		eco_list.put("river", "강");
		eco_list.put("lake", "호수");
		return eco_list;
	}
	@ModelAttribute("roomtype_list")
	public Map<String, String> roomtype_list(){
		Map<String,String> roomtype_list= new LinkedHashMap<String, String>();
		roomtype_list.put("camping", "캠핑");
		roomtype_list.put("caravan", "카라반");
		roomtype_list.put("glamping", "글램핑");
		roomtype_list.put("easycamping", "이지캠핑");
		return roomtype_list;
	}
	@ModelAttribute("theme_list")
	public Map<String, String> theme_list(){
		Map<String,String> theme_list= new LinkedHashMap<String, String>();
		theme_list.put("family", "가족");
		theme_list.put("couple", "연인");
		theme_list.put("kids", "키즈");
		theme_list.put("pet", "반려동물");
		return theme_list;
	}
	
	
}
