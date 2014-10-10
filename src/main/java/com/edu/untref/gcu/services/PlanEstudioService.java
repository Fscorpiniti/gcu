package com.edu.untref.gcu.services;

import java.util.List;

import com.edu.untref.gcu.domain.PlanEstudio;
import com.edu.untref.gcu.domain.PlanMateria;

public interface PlanEstudioService {

	List<PlanEstudio> getAllPlanesByCarrera(Integer idCarrera);

	List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id);

}