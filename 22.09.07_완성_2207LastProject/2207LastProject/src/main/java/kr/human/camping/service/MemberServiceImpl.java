package kr.human.camping.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.SHA256.SHA256;
import kr.human.camping.config.SecurityConfiguration;
import kr.human.camping.dao.MemberDAO;
import kr.human.camping.email.MailService;
import kr.human.camping.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("MemberService")
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private MailService mailService;
	
	@Override
	public void MemberInsert(MemberVO vo) {
		log.debug("회원가입 시작: " + vo);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String password = vo.getPassword();
		try {
			//vo.setPassword(sha256.encrypt(password));
			vo.setPassword(passwordEncoder.encode(password));
			
			// 사용자 데이터를 테이블에 넣는다.
			memberDAO.insert(vo);
			// 사용자 권한 등급은 초기에 unknown을주게되며
			memberDAO.insertAccess(vo);
			
			// 사용자 email로 인증메일이 날라가서 인증을 하게되면 권한등급을 user로 변환해준다.
			// 메일 발송!!!
			mailService.sendEmail(vo.getEmail(), vo.getName(), vo.getId());
			
		} catch (Exception e) {
		}
		//catch(SQLException e) {
			//e.printStackTrace();
		//} 
		/*
			 * catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
			 */
		log.debug("회원가입 종료: ");
	}

	@Override
	public void MemberUpdate(MemberVO vo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = vo.getPassword();
		try {
			if(vo.getPassword() != null||vo.getPassword().length() != 0) {
				vo.setPassword(passwordEncoder.encode(password));
			}else {
				vo.setPassword(null);
			}
			memberDAO.update(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberVO MeberInfo(String id) {
		MemberVO vo = new MemberVO();
		try {
			vo = memberDAO.selectByMemberInfo(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void MemberDelete(MemberVO vo) {
		try {
			memberDAO.delete(vo);
			memberDAO.deleteAccess(vo.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean Login(String id, String password) {
		SHA256 sha256 = new SHA256();
		boolean result = true;
		HashMap<String, String> map = new HashMap<>();
		MemberVO vo = new MemberVO();
		try {
			map.put("id", id);
			map.put("password", sha256.encrypt(password));
			if(memberDAO.IDOverlap(id) != 0) {
				if(memberDAO.login(map) != 0) {
					vo = memberDAO.selectByMemberInfo(id);
					
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public int IDOverlap(String id) {
		int result = 0;
		try {
			result = memberDAO.IDOverlap(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int EmailOverlap(String email) {
		int result = 0;
		try {
			result = memberDAO.EmailOverlap(email);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void changePassword(HashMap<String, String> map) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String result = map.get("password");
		
		try {
			String password = passwordEncoder.encode(result);
			map.put("password",password);
			memberDAO.changePassword(map);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public HashMap<String, String> findID(String email) {
		MemberVO vo = new MemberVO();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			 vo = memberDAO.findID(email);
			 map.put("id",vo.getId());
			 map.put("name", vo.getName());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public boolean passwordcheck(String password, MemberVO vo) {
		boolean Ischeck = true;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			if(!vo.getPassword().equals(passwordEncoder.encode(password))) {
				Ischeck = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Ischeck;
	}

	@Override
	public void updateAccess(String id) {
		
		try {
			memberDAO.updateAccess(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
