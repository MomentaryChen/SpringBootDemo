package com.momentary.demo.service.auth;

import com.momentary.demo.model.auth.User;

public interface AuthService {
	public User getUserByUsername(String username) throws Exception;
}
