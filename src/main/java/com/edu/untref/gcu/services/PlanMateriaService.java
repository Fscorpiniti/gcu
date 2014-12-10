package com.edu.untref.gcu.services;

import java.util.List;

import com.edu.untref.gcu.domain.PlanMateria;

public interface PlanMateriaService {

	List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id);

	PlanMateria findPlanMateriaByIdMateria(Integer idMateria);

}
