package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.PlanEstudio;

@Repository("planEstudioDAO")
public class PlanEstudioDAOImpl extends GenericDAOImpl<PlanEstudio, Serializable> implements PlanEstudioDAO{

	@Override
	protected Class<PlanEstudio> getEntityClass() {
		return PlanEstudio.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PlanEstudio> getAllPlanesByIdCarrera(Integer idCarrera) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.carrera.id = :idCarrera");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("idCarrera", idCarrera);
		
		return query.getResultList();
	}

	@Override
	public PlanEstudio findByIdEntero(String id) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.id = :id");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("id", Integer.valueOf(id));
		
		return (PlanEstudio) query.getSingleResult();
	}

}