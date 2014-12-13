package com.edu.untref.gcu.dtos;

import com.edu.untref.gcu.domain.DiaPlanificacion;

public class NivelPlanificacionDTO {

	private DiaPlanificacion lunes;
	
	private DiaPlanificacion martes;
	
	private DiaPlanificacion miercoles;
	
	private DiaPlanificacion jueves;
	
	private DiaPlanificacion viernes;
	
	private DiaPlanificacion sabado;

	public DiaPlanificacion getLunes() {
		return lunes;
	}

	public void setLunes(DiaPlanificacion lunes) {
		this.lunes = lunes;
	}

	public DiaPlanificacion getMartes() {
		return martes;
	}

	public void setMartes(DiaPlanificacion martes) {
		this.martes = martes;
	}

	public DiaPlanificacion getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(DiaPlanificacion miercoles) {
		this.miercoles = miercoles;
	}

	public DiaPlanificacion getJueves() {
		return jueves;
	}

	public void setJueves(DiaPlanificacion jueves) {
		this.jueves = jueves;
	}

	public DiaPlanificacion getViernes() {
		return viernes;
	}

	public void setViernes(DiaPlanificacion viernes) {
		this.viernes = viernes;
	}

	public DiaPlanificacion getSabado() {
		return sabado;
	}

	public void setSabado(DiaPlanificacion sabado) {
		this.sabado = sabado;
	}

}
