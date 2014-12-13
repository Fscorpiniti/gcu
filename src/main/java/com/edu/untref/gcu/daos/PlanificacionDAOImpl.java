package com.edu.untref.gcu.daos;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Planificacion;

@Repository("planificacionDAO")
public class PlanificacionDAOImpl extends GenericDAOImpl<Planificacion, Serializable> implements PlanificacionDAO {

	@Override
	protected Class<Planificacion> getEntityClass() {
		return Planificacion.class;
	}
	
	@Override
	public Planificacion findByParidad(String paridad) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.paridad = :paridad");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("paridad", paridad);
		
		Planificacion planificacion = null;
		try {
			planificacion = (Planificacion) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		
		return planificacion;
	}

}
