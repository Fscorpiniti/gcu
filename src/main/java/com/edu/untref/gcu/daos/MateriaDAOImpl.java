package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Materia;

@Repository("materiaDAO")
public class MateriaDAOImpl extends GenericDAOImpl<Materia, Serializable> implements MateriaDAO {

	@Override
	protected Class<Materia> getEntityClass() {
		return Materia.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer findIdMateriaByCodigo(Integer codigoMateria) {
		StringBuilder hql = new StringBuilder("select this.id from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.codigo = :codigo");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("codigo", codigoMateria);
		
		List<Integer> codigos = new ArrayList<Integer>();
		try{
			codigos = (List<Integer>) query.getResultList();
		}catch (NoResultException nre){
			codigos = null;
		}
		
		return codigos.iterator().next();
	}
	
	@Override
	public Materia findMateriaByCodigo(Integer codigoMateria) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.codigo = :codigo");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("codigo", codigoMateria);
		
		return (Materia) query.getSingleResult();
	}
	
}
