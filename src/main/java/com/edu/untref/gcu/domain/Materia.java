package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Materia extends PersistibleObject {

	private static final long serialVersionUID = -7331322037056316995L;
	
	@Column(length = 11, nullable = false)
	private Integer codigo;

	@Column(length = 255, nullable = false)
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}