package com.edu.untref.gcu.services;

import java.util.List;

import com.edu.untref.gcu.domain.PlanEstudio;

public interface CarreraService {

	List<PlanEstudio> getAllPlanesByCarrera(Integer idCarrera);

}
