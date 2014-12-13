package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Score extends PersistibleObject {
	
	private static final long serialVersionUID = -1358762961898069864L;

	@Column
	private int cantidadMaterias;
	
	@ManyToMany(fetch = FetchType.EAGER)
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
