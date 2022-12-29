package com.momentary.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

public class LogProcessTImeFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LogManager.getLogger(LogProcessTImeFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		
		filterChain.doFilter(request, response);
		long endTime = System.currentTimeMillis();
		
		logger.info("processTime: " + (endTime - startTime) + " ms");
	}

	
}
