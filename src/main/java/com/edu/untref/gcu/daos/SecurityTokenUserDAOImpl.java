package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.SecurityTokenUser;

@Repository("securityTokenUserDAO")
public class SecurityTokenUserDAOImpl extends
		GenericDAOImpl<SecurityTokenUser, Serializable> implements
		SecurityTokenUserDAO {

	@Override
	protected Class<SecurityTokenUser> getEntityClass() {
		return SecurityTokenUser.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SecurityTokenUser> findByToken(String token) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.token = :tokenRequest");
			
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("tokenRequest", token);
			
		return query.getResultList();
	}

}
