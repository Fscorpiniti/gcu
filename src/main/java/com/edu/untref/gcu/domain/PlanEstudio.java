package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan_de_estudio")
public class PlanEstudio extends PersistibleObject {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 4)
	private Integer anio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Carrera carrera;

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public Carrera getCarrera() {
		return carrera;
	}


	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
}