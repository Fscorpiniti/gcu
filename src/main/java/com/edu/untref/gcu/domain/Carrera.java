package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Carrera extends PersistibleObject{

	private static final long serialVersionUID = 1L;

	@Column(length = 255, nullable = false, columnDefinition = "varchar(255) COLLATE es_ES")
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
