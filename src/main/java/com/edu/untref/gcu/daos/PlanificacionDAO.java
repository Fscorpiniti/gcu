package com.edu.untref.gcu.daos;

import java.io.Serializable;

import com.edu.untref.gcu.domain.Planificacion;

public interface PlanificacionDAO extends GenericDAO<Planificacion, Serializable> {

	Planificacion findByParidad(String paridad);
	
}
