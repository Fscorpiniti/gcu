package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

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

}