package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.edu.untref.gcu.domain.SecurityTokenUser;
import com.edu.untref.gcu.services.SecurityService;

@Component
public class MaestroController {

	@Resource(name = "securityService")
	private SecurityService securityService;
	
	protected List<SecurityTokenUser> validateAccessToken(String token) {
		List<SecurityTokenUser> tokens = this.securityService.findByToken(token);
		
		if (tokens.size() == 0) {
			throw new SecurityException("Unauthorized");
		}
		
		return tokens;
	}
	
	protected void removeToken(String token) {
		List<SecurityTokenUser> validateAccessToken = validateAccessToken(token);
		
		for (SecurityTokenUser tokenUser: validateAccessToken) {
			this.securityService.removeAccessToken(tokenUser.getId());
		}
		
	}
	
}
