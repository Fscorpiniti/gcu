package com.edu.untref.gcu.daos;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Materia;

@Repository("materiaDAO")
public class MateriaDAOImpl extends GenericDAOImpl<Materia, Serializable> implements MateriaDAO {

	@Override
	protected Class<Materia> getEntityClass() {
		return Materia.class;
	}

	@Override
	public Integer findIdMateriaByNombre(String nombreMateria) {
		StringBuilder hql = new StringBuilder("select this.id from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.nombre = :nombre");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("nombre", nombreMateria);
			
		return (Integer) query.getSingleResult();
	}

}
