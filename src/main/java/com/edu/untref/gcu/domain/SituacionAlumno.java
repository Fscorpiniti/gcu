package com.edu.untref.gcu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "situacion_alumno")
public class SituacionAlumno extends PersistibleObject {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	private Alumno alumno;

	@ManyToOne(fetch = FetchType.EAGER)
	private PlanMateria materia;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_situacion")
	private EstadoMateria estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_situacion")
	private Date fecha;

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public PlanMateria getMateria() {
		return materia;
	}

	public void setMateria(PlanMateria materia) {
		this.materia = materia;
	}

	public EstadoMateria getEstado() {
		return estado;
	}

	public void setEstado(EstadoMateria estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}