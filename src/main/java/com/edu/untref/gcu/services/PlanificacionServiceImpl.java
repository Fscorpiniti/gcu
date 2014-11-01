package com.edu.untref.gcu.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.domain.Semana;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;

@Service("planificacionService")
public class PlanificacionServiceImpl implements PlanificacionService {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@Override
	public Semana getPlanificacionPorParidad(String id, String paridad) {

		List<PosiblesCursantesMateriaDTO> materias = planEstudioService.getAllMateriasByCuatrimestre(id, Paridad.valueOf(paridad));
		
		Semana semana = new Semana();

		semana.getLunes().getPosiblesCursantes().add(materias.remove(0));
		semana.getMartes().getPosiblesCursantes().add(materias.remove(0));
		semana.getMiercoles().getPosiblesCursantes().add(materias.remove(0));
		semana.getJueves().getPosiblesCursantes().add(materias.remove(0));
		semana.getViernes().getPosiblesCursantes().add(materias.remove(0));
		semana.getSabado().getPosiblesCursantes().add(materias.remove(0));
		
		return semana;
	}

}
