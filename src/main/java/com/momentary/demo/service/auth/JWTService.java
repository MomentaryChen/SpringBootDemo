package com.momentary.demo.service.auth;

import com.momentary.demo.model.auth.AuthRequest;

public interface JWTService {

	public String generateToken(AuthRequest req);
}
