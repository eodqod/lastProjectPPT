package kr.human.camping.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.human.camping.service.MemberServiceImpl;
import kr.human.camping.service.UserService;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    DataSource dataSource;
	
	private final UserService userService;
	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Enable jdbc authentication
//	@Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		System.out.println("configAuthentication security111");
//		
//		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//		
//		System.out.println("security222");
//		
//        auth
//        .jdbcAuthentication()
//        .passwordEncoder(new BCryptPasswordEncoder())
//        .dataSource(dataSource)
//        .authoritiesByUsernameQuery("select idx, id, role role from member_role where id=?")
//        .getUserDetailsService()
//        //.usersByUsernameQuery("select idx, id, password, name, phone, email, gender from member where id=?")
//        ;
//        
//        System.out.println("security333");
//    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**","/img/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/insert").permitAll()
		.antMatchers("/welcome").permitAll()
		.antMatchers("/Emailwelcome").permitAll()
		.antMatchers("/findID").permitAll()
		.antMatchers("/findIDpage").permitAll()
		.antMatchers("/findPassword").permitAll()
		.antMatchers("/findpage").permitAll()
		.antMatchers("/IdOverlap").permitAll()
		.antMatchers("/EmailOverlap").permitAll()
		.antMatchers("/confilm").permitAll()
		.antMatchers("/chat").permitAll()
		.antMatchers("/MemberInfoUpdate").hasAnyRole("user", "admin")
		.antMatchers("/InfoUpdate").hasAnyRole("user", "admin")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.usernameParameter("id")
		.passwordParameter("password")
		.loginPage("/login").permitAll()
		.loginProcessingUrl("/login_proc") 
		.defaultSuccessUrl("/user_access")
        .successHandler(new MyLoginSuccessHandler())
        .failureUrl("/access_denied")
		.and()
		.logout()
		.invalidateHttpSession(true).logoutSuccessUrl("/");

		http.csrf().disable();
	}
	



}