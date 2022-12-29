package com.momentary.demo.service.auth.impl;

import java.awt.RenderingHints.Key;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.momentary.demo.model.auth.AuthRequest;
import com.momentary.demo.service.auth.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	@Autowired
	private AuthenticationManager authenticationManager;

	private final String KEY = "ThisIsMomentaryDemo";

	@Override
	public String generateToken(AuthRequest req) {
		Authentication auth = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());

		auth = authenticationManager.authenticate(auth);
		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);

		Claims claims = Jwts.claims();
		claims.put("user", UUID.randomUUID().toString().replaceAll("-", ""));
		claims.put("username", userDetails.getUsername());
		claims.setIssuer("Momentary World");

		return Jwts
				.builder()
				.setClaims(claims)
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
	}

}
