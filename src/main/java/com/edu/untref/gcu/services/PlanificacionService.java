package com.edu.untref.gcu.services;

import com.edu.untref.gcu.dtos.PlanificacionDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public interface PlanificacionService {

	PlanificacionDTO planificar(String id, String paridad, Boolean refresh) throws NivelCompletoException;
	
	PlanificacionDTO changePlanificacion(String paridad, Integer idMateria, String nuevoDia);

}
