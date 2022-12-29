package com.momentary.demo.controller.auth;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.momentary.demo.model.auth.AuthRequest;
import com.momentary.demo.model.auth.TokenRes;
import com.momentary.demo.service.auth.DemoUserDetailService;
import com.momentary.demo.service.auth.JWTService;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	
//	@Autowired
//	DemoUserDetailService userDetailsService;
	
	@Autowired
	JWTService jwtService;
	
	@PostMapping(value = "/token")
	public ResponseEntity<String> genToken(HttpServletRequest http, @RequestBody AuthRequest request) {
		
//		UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = jwtService.generateToken(request);
		
		return ResponseEntity.ok().body(token);
	}
	
	@PostMapping(value = "/name")
	public ResponseEntity<String> getName(HttpServletRequest http) {
		
		
		return ResponseEntity.ok().body("Demo");
	}
}
