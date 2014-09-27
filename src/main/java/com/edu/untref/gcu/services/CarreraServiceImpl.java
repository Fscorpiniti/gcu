package com.edu.untref.gcu.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.domain.PlanEstudio;

@Service("carreraService")
public class CarreraServiceImpl implements CarreraService {
	
	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@Override
	public List<PlanEstudio> getAllPlanesByCarrera(Integer idCarrera) {
		return planEstudioService.getAllPlanesByCarrera(idCarrera);
	}

	
}
