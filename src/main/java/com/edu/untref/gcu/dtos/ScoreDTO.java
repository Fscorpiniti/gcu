package com.edu.untref.gcu.dtos;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreDTO {
	
	@JsonProperty("cantidad_materias")
	private int cantidadMaterias;

	private List<Alumno> alumnos = new ArrayList<Alumno>();

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public int getCantidadMaterias() {
		return cantidadMaterias;
	}

	public void setCantidadMaterias(int cantidadMaterias) {
		this.cantidadMaterias = cantidadMaterias;
	}
	
}
