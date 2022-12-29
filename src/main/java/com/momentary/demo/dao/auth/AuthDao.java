package com.momentary.demo.dao.auth;

import com.momentary.demo.model.auth.User;

public interface AuthDao {
	
	public User getUserByUsername(String username);
	
}
