package com.edu.untref.gcu.services;

import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public interface PlanificacionService {

	PlanificacionCuatrimestreDTO planificar(String id, String paridad) throws NivelCompletoException;

}
