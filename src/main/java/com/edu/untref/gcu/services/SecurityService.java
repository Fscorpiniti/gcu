package com.edu.untref.gcu.services;

import java.util.List;

import com.edu.untref.gcu.domain.SecurityTokenUser;

public interface SecurityService {

	void save(SecurityTokenUser securityTokenUser);
	
	List<SecurityTokenUser> findByToken(String token);

	void removeAccessToken(Integer id);
	
}
