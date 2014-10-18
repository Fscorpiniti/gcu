package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno extends PersistibleObject {

	private static final long serialVersionUID = 1L;

	@Column(length = 255, nullable = false)
	private String nombre;

	@Column(length = 255, nullable = false)
	private String apellido;

	@Column(nullable = false, length = 5)
	private Integer legajo;

	@ManyToOne(fetch = FetchType.EAGER)
	private PlanEstudio planEstudio;

	@Column(nullable = false, length = 4)
	private Integer anioIngreso;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public PlanEstudio getPlanEstudio() {
		return planEstudio;
	}

	public void setPlanEstudio(PlanEstudio planEstudio) {
		this.planEstudio = planEstudio;
	}

	public Integer getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(Integer anioIngreso) {
		this.anioIngreso = anioIngreso;
	}
}
