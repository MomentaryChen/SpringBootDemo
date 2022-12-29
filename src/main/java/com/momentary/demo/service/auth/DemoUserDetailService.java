package com.momentary.demo.service.auth;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DemoUserDetailService implements UserDetailsService{
	private static final Logger logger = LogManager.getLogger(DemoUserDetailService.class);
	
	@Autowired
	AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.info("=================== loadUserByUsername ===================\"");
		try {
			UserDetails user = authService.getUserByUsername(username);
			if(Objects.isNull(user)) {
				throw new UsernameNotFoundException("User is wrong.");
			}
			
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UsernameNotFoundException("User is wrong.");
		}
		
		
	}
	
}
