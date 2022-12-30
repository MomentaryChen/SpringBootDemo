package com.momentary.demo.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.momentary.demo.model.auth.User;
import com.momentary.demo.service.auth.DemoUserDetailService;
import com.momentary.demo.service.auth.JWTService;

@Component
public class DemoAuthenticationFilter extends OncePerRequestFilter{
	private static final Logger logger = LogManager.getLogger(DemoAuthenticationFilter.class);
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	DemoUserDetailService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(!Objects.isNull(authHeader) && !authHeader.isEmpty()) {
			String token = authHeader.replace("Bearer ", "");
			
			Map<Object, Object> claims;
			try {

				claims = jwtService.parserToken(token);
				String username = (String) claims.get("username");
				
				User user = (User) userDetailsService.loadUserByUsername(username);
				if(!Objects.isNull(user)) {
					Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		chain.doFilter(request, response);
	}
	
	

}
