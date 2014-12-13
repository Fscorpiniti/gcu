package com.edu.untref.gcu.dtos;

import com.edu.untref.gcu.domain.PlanMateria;


public class MateriaDiaDTO {
	
	private PlanMateria materia;

	private int dia;
		
	public PlanMateria getMateria() {
		return materia;
	}

	public void setMateria(PlanMateria materia) {
		this.materia = materia;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

}
