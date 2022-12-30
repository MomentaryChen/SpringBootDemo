package com.momentary.demo.service.auth;

import java.util.Map;

import com.momentary.demo.model.auth.AuthRequest;

public interface JWTService {

	public String generateToken(AuthRequest req);
	
	public Map<Object, Object> parserToken(String token) throws Exception;
}
