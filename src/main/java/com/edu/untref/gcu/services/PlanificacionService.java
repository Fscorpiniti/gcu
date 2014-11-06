package com.edu.untref.gcu.services;

import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;

public interface PlanificacionService {

	PlanificacionCuatrimestreDTO planificar(String id, String paridad);

}
