package com.edu.untref.gcu.dtos;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.MateriaDiaDTO;
import com.edu.untref.gcu.domain.PlanMateria;

public class CursadaAlumnoDTO {
	
	private Alumno alumno;
	
	private List<MateriaDiaDTO> materias = new ArrayList<MateriaDiaDTO>();

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<MateriaDiaDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaDiaDTO> materias) {
		this.materias = materias;
	}

	public boolean notContainsMateriaDia(int dia) {
		
		for (MateriaDiaDTO unaMateria: materias) {
			if (unaMateria.getDia() == dia) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

	public boolean notContainsMateria(PlanMateria planMateria) {
		
		for (MateriaDiaDTO unaMateria: materias) {
			if (unaMateria.getMateria().equals(planMateria)) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}
	
}
