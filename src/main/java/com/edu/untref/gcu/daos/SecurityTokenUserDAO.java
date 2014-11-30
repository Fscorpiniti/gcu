package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.SecurityTokenUser;

public interface SecurityTokenUserDAO extends GenericDAO<SecurityTokenUser, Serializable> {

	List<SecurityTokenUser> findByToken(String token);

}
