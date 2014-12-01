package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.EstadoMateria;
import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.domain.SituacionAlumno;

@Repository("situacionAlumnoDAO")
public class SituacionAlumnoDAOImpl extends
		GenericDAOImpl<SituacionAlumno, Serializable> implements SituacionAlumnoDAO {

	@Override
	protected Class<SituacionAlumno> getEntityClass() {
		return SituacionAlumno.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Alumno> findNoPosiblesCursantes(PlanMateria materia) {
		StringBuilder hql = new StringBuilder("select this.alumno from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.materia = :materiaParam");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("materiaParam", materia);
		
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Alumno> findAlumnosCorrelacionHabilitada(PlanMateria correlativa) {
		StringBuilder hql = new StringBuilder("select this.alumno from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.materia = :correlativa and (this.estado = '" +
				EstadoMateria.APROBADO + "' or this.estado = '" + EstadoMateria.REGULAR + "')");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("correlativa", correlativa);
		
		return query.getResultList();
	}

	@Override
	public SituacionAlumno findSituacionByIdPlanMateriaAndIdAlumno(Integer idPlanMateria, Alumno alumno) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this where this.materia.id = :idPlanMateria and this.alumno.id = :idAlumno");
		
		Query query = this.getEntityManager().createQuery(hql.toString());
		query.setParameter("idPlanMateria", idPlanMateria);
		query.setParameter("idAlumno", alumno.getId());
		
		SituacionAlumno situacionAlumno;
		try{
			situacionAlumno = (SituacionAlumno) query.getSingleResult();
		}catch (NoResultException nre){
			situacionAlumno = null;
		}
		return situacionAlumno;
	}

}