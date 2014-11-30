package com.edu.untref.gcu.daos;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Usuario;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Serializable> implements UsuarioDAO {

	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

	@Override
	public Usuario findByUsername(String username) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.userName = :user");
			
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("user", username);
			
		return (Usuario) query.getSingleResult();
	}

}
