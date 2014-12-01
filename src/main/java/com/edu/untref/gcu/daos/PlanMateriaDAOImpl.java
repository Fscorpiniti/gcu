package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.PlanMateria;

@Repository("planMateriaDAO")
public class PlanMateriaDAOImpl extends GenericDAOImpl<PlanMateria, Serializable> implements PlanMateriaDAO{

	@Override
	protected Class<PlanMateria> getEntityClass() {
		return PlanMateria.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.planEstudio.id = :idPlan");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("idPlan", Integer.valueOf(id));
		
		return query.getResultList();
	}

	@Override
	public PlanMateria findPlanMateriaByIdMateria(Integer idMateria) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.materia.id = :idMateria");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("idMateria", idMateria);
			
		return (PlanMateria) query.getSingleResult();
	}

}
