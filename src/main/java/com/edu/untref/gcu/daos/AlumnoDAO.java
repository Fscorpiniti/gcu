package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;

public interface AlumnoDAO extends GenericDAO<Alumno, Serializable> {

	List<Alumno> findDifferenceList(List<Alumno> noPosiblesCursantes);
	Alumno findByLejago(Integer legajo);

}
