package com.edu.untref.gcu.dtos;

import com.edu.untref.gcu.domain.PlanMateria;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PosiblesCursantesMateriaDTO {

	@JsonProperty(value = "cantidad_cursantes")
	private Integer cantidadCursantes;
	
	private PlanMateria materia;

	public PosiblesCursantesMateriaDTO(PlanMateria materia, int size) {
		this.materia = materia;
		this.cantidadCursantes = size;
	}

	public Integer getCantidadCursantes() {
		return cantidadCursantes;
	}

	public void setCantidadCursantes(Integer cantidadCursantes) {
		this.cantidadCursantes = cantidadCursantes;
	}

	public PlanMateria getMateria() {
		return materia;
	}

	public void setMateria(PlanMateria materia) {
		this.materia = materia;
	}	
}