package kr.human.camping.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/*
 	id varchar2(100) PRIMARY KEY,
	idx NUMBER,
	password varchar2(100) NOT NULL,
	name varchar2(100) NOT NULL,
	phone NUMBER(11) NOT NULL,
	email varchar2(100) NOT NULL,
	gender char(1) check(gender IN('0','1'))NOT NULL,
 */

@Data
public class MemberVO implements UserDetails{

	private String id;
	private long idx;
	private String password;
	private String name;
	private String phone;
	private String email;
	private short gender;
	private String role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}
	
	@Override
	public String getPassword() {
	    return this.password;
	}
	
	 // 시큐리티의 userName
    // -> 따라서 얘는 인증할 때 id를 봄
    public String getUserName() {
        return this.id;
    }
    
    // Vo의 userName !
	@Override
	public String getUsername() {
		return this.name;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
