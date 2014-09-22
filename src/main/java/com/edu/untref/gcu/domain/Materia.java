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
	
	@Column(length = 11, nullable = false)
	private Integer cuatrimestre;
	
	@Column(length = 11, nullable = false)
	private Integer anio;
	
	@Column(length = 11, nullable = false)
	private Integer horas;

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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	
}