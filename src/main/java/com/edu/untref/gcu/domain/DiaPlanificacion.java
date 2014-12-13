package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "dia_planificacion")
public class DiaPlanificacion extends PersistibleObject {
	
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanMateria materia;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Alumno> cursantes = new ArrayList<Alumno>();
	
	@Column(name = "dia_cursada")
	private Integer dia;

	public PlanMateria getMateria() {
		return materia;
	}

	public void setMateria(PlanMateria materia) {
		this.materia = materia;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public List<Alumno> getCursantes() {
		return cursantes;
	}

	public void setCursantes(List<Alumno> cursantes) {
		this.cursantes = cursantes;
	}
	
}