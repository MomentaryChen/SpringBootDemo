package com.momentary.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LogAPIFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LogManager.getLogger(LogAPIFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);
		
		filterChain.doFilter(request, response);
		
		int httpStatus = res.getStatus();
		String url = req.getRequestURI();
		String httpMethod = req.getMethod();
		String params = req.getQueryString();
		
		logger.info(String.join(" ", url, String.valueOf(httpStatus), httpMethod, params));
	}
}
