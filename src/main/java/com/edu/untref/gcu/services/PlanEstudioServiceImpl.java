package com.edu.untref.gcu.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.PlanEstudioDAO;
import com.edu.untref.gcu.domain.PlanEstudio;
import com.edu.untref.gcu.domain.PlanMateria;

@Service("planEstudioService")
public class PlanEstudioServiceImpl implements PlanEstudioService {

	@Resource(name = "planEstudioDAO")
	private PlanEstudioDAO planEstudioDAO;
	
	@Resource(name = "planMateriaService")
	private PlanMateriaService planMateriaService;
	
	public List<PlanEstudio> getAllPlanesByCarrera(Integer idCarrera) {
		return this.planEstudioDAO.getAllPlanesByIdCarrera(idCarrera);
	}

	@Override
	public List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id) {
		return planMateriaService.getAllPlanMateriasByIdPlanEstudio(id);
	}
	
}