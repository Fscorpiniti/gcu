package com.edu.untref.gcu.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.PlanMateriaDAO;
import com.edu.untref.gcu.domain.PlanMateria;

@Service("planMateriaService")
public class PlanMateriaServiceImpl implements PlanMateriaService {

	@Resource(name= "planMateriaDAO")
	private PlanMateriaDAO planMateriaDAO;
	
	@Override
	public List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id) {
		return this.planMateriaDAO.getAllPlanMateriasByIdPlanEstudio(id);
	}

}
