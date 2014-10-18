package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Alumno;

@Repository("alumnoDAO")
public class AlumnoDAOImpl extends GenericDAOImpl<Alumno, Serializable>
		implements AlumnoDAO {

	@Override
	protected Class<Alumno> getEntityClass() {
		return Alumno.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> findDifferenceList(List<Alumno> noPosiblesCursantes) {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(getEntityClass().getName());
		hql.append(" this");

		Query query = null;
		if (noPosiblesCursantes.isEmpty()) {
			query = this.getEntityManager().createQuery(hql.toString());
		} else {
			hql.append(" where this not in (:difference)");
			query = this.getEntityManager().createQuery(hql.toString());
			query.setParameter("difference", noPosiblesCursantes);
		}

		return query.getResultList();
	}

}
