package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.domain.SituacionAlumno;

public interface SituacionAlumnoDAO extends GenericDAO<SituacionAlumno, Serializable> {

	List<Alumno> findNoPosiblesCursantes(PlanMateria materia);

	List<Alumno> findAlumnosCorrelacionHabilitada(PlanMateria correlativa);

}