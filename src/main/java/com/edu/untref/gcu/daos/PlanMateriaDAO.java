package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.PlanMateria;

public interface PlanMateriaDAO extends GenericDAO<PlanMateria, Serializable>{

	List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id);
	List<PlanMateria> getAllPlanMateriaByMateria(String id);
	}
