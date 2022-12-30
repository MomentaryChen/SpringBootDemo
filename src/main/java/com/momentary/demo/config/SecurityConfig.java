package com.momentary.demo.config;

import java.lang.invoke.ConstantCallSite;

import javax.servlet.Filter;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserAuthorityChanger;

import com.momentary.demo.constant.Constants;
import com.momentary.demo.service.auth.DemoUserDetailService;
import com.momentary.demo.filter.DemoAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DemoUserDetailService detailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, "/api/auth/*").permitAll()
		.antMatchers(HttpMethod.GET, "/product/*").permitAll()
		.antMatchers(HttpMethod.GET, "/products").hasAuthority(Constants.Role_Authority.ADMIN.getRole())
		.antMatchers(HttpMethod.POST, "/products").authenticated()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
		
		http.headers().cacheControl();
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(detailService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Filter authenticationTokenFilterBean() throws Exception {
		return new DemoAuthenticationFilter();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
}
