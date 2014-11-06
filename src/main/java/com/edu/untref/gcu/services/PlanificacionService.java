package com.edu.untref.gcu.services;

import java.util.List;

import com.edu.untref.gcu.domain.Semana;
import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;

public interface PlanificacionService {

	Semana getPlanificacionPorParidad(String id, String paridad);

	List<NivelPlanificacionDTO> planificar(String idPlan, String anio, String cuatrimestre);

}
