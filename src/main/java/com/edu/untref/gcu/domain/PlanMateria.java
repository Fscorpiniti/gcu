package com.edu.untref.gcu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "plan_materia")
public class PlanMateria extends PersistibleObject {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 1)
	private Integer anio;

	@Column(nullable = false, length = 3)
	private Integer horas;

	@Column(nullable = false, length = 1)
	private Integer cuatrimestre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Materia materia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Materia correlativa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanEstudio planEstudio;

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
	
	public Integer getHoras() {
		return horas;
	}
	
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	
	public Materia getMateria() {
		return materia;
	}
	
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public Materia getCorrelativa() {
		return correlativa;
	}
	
	public void setCorrelativa(Materia correlativa) {
		this.correlativa = correlativa;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PlanEstudio getPlanEstudio() {
		return planEstudio;
	}

	public void setPlanEstudio(PlanEstudio planEstudio) {
		this.planEstudio = planEstudio;
	}
}
