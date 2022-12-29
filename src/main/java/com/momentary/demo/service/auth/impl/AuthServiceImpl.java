package com.momentary.demo.service.auth.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momentary.demo.dao.auth.AuthDao;
import com.momentary.demo.model.auth.User;
import com.momentary.demo.service.auth.AuthService;
import com.momentary.demo.util.ExceptionUtils;

@Service
public class AuthServiceImpl implements AuthService {
	private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);
	
	@Autowired
	AuthDao authDao;
	
	public User getUserByUsername(String username) {
		logger.info("=================== Start get user by username ===================");
		User user = null;
		try {
			user = authDao.getUserByUsername(username);
		}catch (Exception e) {
			ExceptionUtils.getStackTrace(e);
		}finally {
			logger.info("=================== End get user by username ===================");
		}
		 
		return user;
		
	}
}
