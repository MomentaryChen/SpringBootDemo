package com.momentary.demo.config;

import java.lang.invoke.ConstantCallSite;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserAuthorityChanger;

import com.momentary.demo.constant.Constants;
import com.momentary.demo.service.auth.DemoUserDetailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DemoUserDetailService detailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, "/api/auth/*").permitAll()
//		.antMatchers(HttpMethod.GET, "/products").hasAuthority(Constants.RoleAuthority.ADMIN.getRole())
		.antMatchers(HttpMethod.GET, "/products/*").authenticated()
		.antMatchers(HttpMethod.POST, "/products").authenticated()
//		.anyRequest().authenticated()
		.and()
		.csrf().disable();
		
//		http.addFilterBefore(authenticationManagerBean(), AuthenticationFilter.class);
		http.headers().cacheControl();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(detailService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
}
