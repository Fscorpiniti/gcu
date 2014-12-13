package com.edu.untref.gcu.dtos;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.domain.Score;

public class PlanificacionDTO {

	private String paridad;
	
	private List<NivelPlanificacionDTO> cuatrimestre1 = new ArrayList<NivelPlanificacionDTO>();
	
	private List<NivelPlanificacionDTO> cuatrimestre2 = new ArrayList<NivelPlanificacionDTO>();
	
	private List<NivelPlanificacionDTO> cuatrimestre3 = new ArrayList<NivelPlanificacionDTO>();
	
	private List<NivelPlanificacionDTO> cuatrimestre4 = new ArrayList<NivelPlanificacionDTO>();
	
	private List<NivelPlanificacionDTO> cuatrimestre5 = new ArrayList<NivelPlanificacionDTO>();
	
	private List<Score> scores = new ArrayList<Score>();
	
	private Integer score;
	
	private Integer scoreCuatrimestre1;
	
	private Integer scoreCuatrimestre2;
	
	private Integer scoreCuatrimestre3;
	
	private Integer scoreCuatrimestre4;
	
	private Integer scoreCuatrimestre5;
	
	private String fechaActualizacion; 
	
	public String getParidad() {
		return paridad;
	}

	public void setParidad(String paridad) {
		this.paridad = paridad;
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

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public List<NivelPlanificacionDTO> getCuatrimestre1() {
		return cuatrimestre1;
	}

	public void setCuatrimestre1(List<NivelPlanificacionDTO> cuatrimestre1) {
		this.cuatrimestre1 = cuatrimestre1;
	}

	public List<NivelPlanificacionDTO> getCuatrimestre2() {
		return cuatrimestre2;
	}

	public void setCuatrimestre2(List<NivelPlanificacionDTO> cuatrimestre2) {
		this.cuatrimestre2 = cuatrimestre2;
	}

	public List<NivelPlanificacionDTO> getCuatrimestre3() {
		return cuatrimestre3;
	}

	public void setCuatrimestre3(List<NivelPlanificacionDTO> cuatrimestre3) {
		this.cuatrimestre3 = cuatrimestre3;
	}

	public List<NivelPlanificacionDTO> getCuatrimestre4() {
		return cuatrimestre4;
	}

	public void setCuatrimestre4(List<NivelPlanificacionDTO> cuatrimestre4) {
		this.cuatrimestre4 = cuatrimestre4;
	}

	public List<NivelPlanificacionDTO> getCuatrimestre5() {
		return cuatrimestre5;
	}

	public void setCuatrimestre5(List<NivelPlanificacionDTO> cuatrimestre5) {
		this.cuatrimestre5 = cuatrimestre5;
	}

	
	
}
