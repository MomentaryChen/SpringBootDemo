package com.momentary.demo.service.auth.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.momentary.demo.constant.Constants;
import com.momentary.demo.model.auth.User;
import com.momentary.demo.service.auth.UserIdentityService;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {
	
	private final String anonymousUser = "anonymousUser";

	private final User EMPTY_USER = new User(Constants.Role_Authority.ANONYMOUS.getRole(), "", null);

	private User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		return principal.equals(anonymousUser) ? EMPTY_USER : (User) principal;
	}

	@Override
	public Boolean isAnonymous() {
		// TODO Auto-generated method stub
		return getUser().equals(EMPTY_USER);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUser().getUsername();
	}

}
