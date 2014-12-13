package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Planificacion extends PersistibleObject {
	
	private static final long serialVersionUID = 1L;

	@Column
	private String paridad;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "semana_cuatrimestre_1", joinColumns = { 
	@JoinColumn(name = "planificacion_id", nullable = true, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "id", nullable = true, updatable = false) })
	private List<SemanaPlanificacion> cuatrimestre1 = new ArrayList<SemanaPlanificacion>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "semana_cuatrimestre_2", joinColumns = { 
	@JoinColumn(name = "planificacion_id", nullable = true, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "id", nullable = true, updatable = false) })
	private List<SemanaPlanificacion> cuatrimestre2 = new ArrayList<SemanaPlanificacion>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "semana_cuatrimestre_3", joinColumns = { 
	@JoinColumn(name = "planificacion_id", nullable = true, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "id", nullable = true, updatable = false) })
	private List<SemanaPlanificacion> cuatrimestre3 = new ArrayList<SemanaPlanificacion>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "semana_cuatrimestre_4", joinColumns = { 
	@JoinColumn(name = "planificacion_id", nullable = true, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "id", nullable = true, updatable = false) })
	private List<SemanaPlanificacion> cuatrimestre4 = new ArrayList<SemanaPlanificacion>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "semana_cuatrimestre_5", joinColumns = { 
	@JoinColumn(name = "planificacion_id", nullable = true, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "id", nullable = true, updatable = false) })
	private List<SemanaPlanificacion> cuatrimestre5 = new ArrayList<SemanaPlanificacion>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Score> scores = new ArrayList<Score>();
	
	@Column
	private Integer score;
	
	@Column
	private Integer scoreCuatrimestre1;
	
	@Column
	private Integer scoreCuatrimestre2;
	
	@Column
	private Integer scoreCuatrimestre3;
	
	@Column
	private Integer scoreCuatrimestre4;
	
	@Column
	private Integer scoreCuatrimestre5;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ultima_actualizacion")
	private Date fechaActualizacion; 
	
	public Planificacion() {
		
	}

	public String getParidad() {
		return paridad;
	}

	public void setParidad(String paridad) {
		this.paridad = paridad;
	}

	public List<SemanaPlanificacion> getCuatrimestre1() {
		return cuatrimestre1;
	}

	public void setCuatrimestre1(List<SemanaPlanificacion> cuatrimestre1) {
		this.cuatrimestre1 = cuatrimestre1;
	}

	public List<SemanaPlanificacion> getCuatrimestre2() {
		return cuatrimestre2;
	}

	public void setCuatrimestre2(List<SemanaPlanificacion> cuatrimestre2) {
		this.cuatrimestre2 = cuatrimestre2;
	}

	public List<SemanaPlanificacion> getCuatrimestre3() {
		return cuatrimestre3;
	}

	public void setCuatrimestre3(List<SemanaPlanificacion> cuatrimestre3) {
		this.cuatrimestre3 = cuatrimestre3;
	}

	public List<SemanaPlanificacion> getCuatrimestre4() {
		return cuatrimestre4;
	}

	public void setCuatrimestre4(List<SemanaPlanificacion> cuatrimestre4) {
		this.cuatrimestre4 = cuatrimestre4;
	}

	public List<SemanaPlanificacion> getCuatrimestre5() {
		return cuatrimestre5;
	}

	public void setCuatrimestre5(List<SemanaPlanificacion> cuatrimestre5) {
		this.cuatrimestre5 = cuatrimestre5;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScoreCuatrimestre1() {
		return scoreCuatrimestre1;
	}

	public void setScoreCuatrimestre1(Integer scoreCuatrimestre1) {
		this.scoreCuatrimestre1 = scoreCuatrimestre1;
	}

	public Integer getScoreCuatrimestre2() {
		return scoreCuatrimestre2;
	}

	public void setScoreCuatrimestre2(Integer scoreCuatrimestre2) {
		this.scoreCuatrimestre2 = scoreCuatrimestre2;
	}

	public Integer getScoreCuatrimestre3() {
		return scoreCuatrimestre3;
	}

	public void setScoreCuatrimestre3(Integer scoreCuatrimestre3) {
		this.scoreCuatrimestre3 = scoreCuatrimestre3;
	}

	public Integer getScoreCuatrimestre4() {
		return scoreCuatrimestre4;
	}

	public void setScoreCuatrimestre4(Integer scoreCuatrimestre4) {
		this.scoreCuatrimestre4 = scoreCuatrimestre4;
	}

	public Integer getScoreCuatrimestre5() {
		return scoreCuatrimestre5;
	}

	public void setScoreCuatrimestre5(Integer scoreCuatrimestre5) {
		this.scoreCuatrimestre5 = scoreCuatrimestre5;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
