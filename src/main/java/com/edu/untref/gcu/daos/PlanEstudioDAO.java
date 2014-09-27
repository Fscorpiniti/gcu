package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.PlanEstudio;

public interface PlanEstudioDAO extends GenericDAO<PlanEstudio, Serializable>{

	List<PlanEstudio> getAllPlanesByIdCarrera(Integer idCarrera);

}
