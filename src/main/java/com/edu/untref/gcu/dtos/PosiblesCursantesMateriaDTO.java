package com.edu.untref.gcu.dtos;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.PlanMateria;

public class PosiblesCursantesMateriaDTO implements
		Comparable<PosiblesCursantesMateriaDTO> {

	private PlanMateria materia;

	private List<Alumno> alumnosPosiblesCursantes = new ArrayList<Alumno>();

	public PosiblesCursantesMateriaDTO(PlanMateria materia,
			List<Alumno> alumnosPosiblesCursantes) {
		this.materia = materia;
		this.alumnosPosiblesCursantes = alumnosPosiblesCursantes;
	}

	public PlanMateria getMateria() {
		return materia;
	}

	public void setMateria(PlanMateria materia) {
		this.materia = materia;
	}

	public List<Alumno> getAlumnosPosiblesCursantes() {
		return alumnosPosiblesCursantes;
	}

	@Override
	public int compareTo(PosiblesCursantesMateriaDTO posibleCursante) {

		int resultado;

		if (this.getAlumnosPosiblesCursantes().size() > posibleCursante
				.getAlumnosPosiblesCursantes().size()) {

			resultado = -1;

		} else if (this.getAlumnosPosiblesCursantes().size() == posibleCursante
				.getAlumnosPosiblesCursantes().size()) {

			resultado = 0;

		} else {

			resultado = 1;
		}

		return resultado;
	}
}