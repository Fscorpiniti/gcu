package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.services.PlanEstudioService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/planes_estudio")
@Api(value = "planEstudioController", description = "EndPoint que permite realizar acciones sobre las planes de estudio.")
public class PlanEstudioController {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@ResponseBody
	@RequestMapping(value = "/{id}/plan_materias", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve todos los planes de las materias, que incluye cuatrimestre, anio, carga horaria, correlativa dentro de un determinado plan de estudios.")
	public List<PlanMateria> getAllMaterias(
			@ApiParam(name = "id", required = true) @PathVariable String id) {
		
		return planEstudioService.getAllPlanMateriasByIdPlanEstudio(id);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/plan_materias/posibles_cursantes", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve todos los planes de las materias, que incluye cuatrimestre, anio, carga horaria, correlativa dentro de un determinado plan de estudios."
			+ " Ademas los posibles cursantes de cada materia.")
	public List<PosiblesCursantesMateriaDTO> getAllPosiblesCursantesMaterias(
			@ApiParam(name = "id", required = true) @PathVariable String id) {
		
		return planEstudioService.getAllPosiblesCursantesMaterias(id);
	}

}
