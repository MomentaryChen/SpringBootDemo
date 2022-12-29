package com.momentary.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.momentary.demo.filter.LogProcessTImeFilter;
import com.momentary.demo.filter.LogAPIFilter;
@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LogProcessTImeFilter> logProcessTimeFilter() {
		FilterRegistrationBean<LogProcessTImeFilter> bean = new FilterRegistrationBean<>();
		
		bean.setFilter(new LogProcessTImeFilter());
		bean.addUrlPatterns("/*");
		bean.setName("LogProcessTImeFilter");
		bean.setOrder(100);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<LogAPIFilter> LogAPIFilter() {
		FilterRegistrationBean<LogAPIFilter> bean = new FilterRegistrationBean<>();
		
		bean.setFilter(new LogAPIFilter());
		bean.addUrlPatterns("/*");
		bean.setName("LogAPIFilter");
		bean.setOrder(101);
		
		return bean;
	}
	
	
	

}
