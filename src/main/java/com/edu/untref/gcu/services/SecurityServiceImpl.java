package com.edu.untref.gcu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.SecurityTokenUserDAO;
import com.edu.untref.gcu.domain.SecurityTokenUser;

@Transactional
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityTokenUserDAO securityTokenUserDAO;
	
	public void save(SecurityTokenUser securityTokenUser) {
		securityTokenUserDAO.save(securityTokenUser);
	}
	
	public List<SecurityTokenUser> findByToken(String token) {
		return securityTokenUserDAO.findByToken(token);
	}

	@Override
	public void removeAccessToken(Integer id) {
		this.securityTokenUserDAO.remove(id);		
	}
	
}