package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "semana_planificacion")
public class SemanaPlanificacion extends PersistibleObject {
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<DiaPlanificacion> dias = new ArrayList<DiaPlanificacion>();
	
	public List<DiaPlanificacion> getDias() {
		return dias;
	}

	public void setDias(List<DiaPlanificacion> dias) {
		this.dias = dias;
	}
	
}