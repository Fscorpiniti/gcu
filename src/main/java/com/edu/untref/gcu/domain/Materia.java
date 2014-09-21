package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Materia extends PersistibleObject {

	private static final long serialVersionUID = -7331322037056316995L;

	@Column(length = 100)
	private String nombre;
	
	@Column
	private Integer cuatrimestre;
	
	@Column
	private Integer anio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCuatrimestre() {
		return cuatrimestre;
	}

	public void setCuatrimestre(Integer cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
}