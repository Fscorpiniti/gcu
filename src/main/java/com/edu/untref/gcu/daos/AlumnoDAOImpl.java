package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Alumno;

@Repository("alumnoDAO")
public class AlumnoDAOImpl extends GenericDAOImpl<Alumno, Serializable> implements AlumnoDAO {

	@Override
	protected Class<Alumno> getEntityClass() {
		return Alumno.class;
	}

}
