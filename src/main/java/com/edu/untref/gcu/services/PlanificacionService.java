package com.edu.untref.gcu.services;

import com.edu.untref.gcu.domain.Semana;

public interface PlanificacionService {

	public Semana getPlanificacionPorParidad(String id, String paridad);

}
