package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.SituacionAlumno;

@Repository("situacionAlumnoDAO")
public class SituacionAlumnoDAOImpl extends
		GenericDAOImpl<SituacionAlumno, Serializable> implements SituacionAlumnoDAO {

	@Override
	protected Class<SituacionAlumno> getEntityClass() {
		return SituacionAlumno.class;
	}

}
