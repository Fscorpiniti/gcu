package com.edu.untref.gcu.services;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.edu.untref.gcu.domain.SecurityTokenUser;
import com.edu.untref.gcu.security.TokenGenerator;

@Transactional
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	protected AuthenticationManager authenticationManager;
	
	@Resource(name = "securityService")
	private SecurityService securityService;
	
	@Override
	public String authenticate(String userName, String password) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password);
		authenticationManager.authenticate(authentication);
		
		String token = TokenGenerator.generate();
		SecurityTokenUser securityTokenUser = new SecurityTokenUser(userName, token);
		securityService.save(securityTokenUser);
		
		return token;
	}

}
