package com.momentary.demo.model.auth;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TokenRes {
	
	
	String token;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime tokenGenTime;
	
	public TokenRes(String token, LocalDateTime tokenGenTime) {
		super();
		this.token = token;
		this.tokenGenTime = tokenGenTime;
	}
	public LocalDateTime getTokenGenTime() {
		return tokenGenTime;
	}
	public void setTokenGenTime(LocalDateTime tokenGenTime) {
		this.tokenGenTime = tokenGenTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
