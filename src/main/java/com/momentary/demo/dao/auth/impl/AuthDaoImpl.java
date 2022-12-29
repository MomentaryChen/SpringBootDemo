package com.momentary.demo.dao.auth.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.momentary.demo.dao.auth.AuthDao;
import com.momentary.demo.model.auth.User;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Repository
public class AuthDaoImpl implements AuthDao {
	private static final Logger logger = LogManager.getLogger(AuthDaoImpl.class);

	private final List<User> users = new ArrayList<User>();

	public AuthDaoImpl() {
		
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("User"));
		
		users.add(new User("victor", "$2a$12$WsO1/lCJR35FdQpLBoFE/.hfoaX2RzRs3Q2icuv2fX90B28aTDkGi", authorities));
		users.add(new User("joe", "$2a$12$WsO1/lCJR35FdQpLBoFE/.hfoaX2RzRs3Q2icuv2fX90B28aTDkGi", authorities));
		users.add(new User("abby", "$2a$12$WsO1/lCJR35FdQpLBoFE/.hfoaX2RzRs3Q2icuv2fX90B28aTDkGi", authorities));

		logger.info("Init Auth users");
	}

	@Override
	public User getUserByUsername(String username) {
		
		Optional<User> user = users
				.stream()
				.filter((userTemp) -> userTemp.getUsername().equals(username))
				.findAny();
		
		
		return user.orElse(null);
	}

}
