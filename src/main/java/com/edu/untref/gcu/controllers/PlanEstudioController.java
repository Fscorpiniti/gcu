package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.services.PlanEstudioService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/planes")
@Api(value = "planEstudioController", description = "EndPoint que permite realizar acciones sobre los planes de estudio.")
public class PlanEstudioController extends MaestroController {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@ResponseBody
	@RequestMapping(value = "/{id}/materias", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ApiOperation(value = "Devuelve todos los planes de las materias, que incluye cuatrimestre, anio, " +
			"carga horaria, correlativa dentro de un determinado plan de estudios.")
	public List<PlanMateria> getAllMaterias(@RequestParam("access_token") String token, 
			@ApiParam(name = "id", required = true) @PathVariable String id) {
		
		this.validateAccessToken(token);
		
		return planEstudioService.getAllPlanMateriasByIdPlanEstudio(id);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/materias/probables-cursantes", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ApiOperation(value = "Devuelve todos los planes de las materias, que incluye cuatrimestre, anio, carga horaria, " +
			"correlativa dentro de un determinado plan de estudios. Ademas los posibles cursantes de cada materia.")
	public List<PosiblesCursantesMateriaDTO> getAllPosiblesCursantesMaterias(@RequestParam("access_token") String token, 
			@ApiParam(name = "id", required = true) @PathVariable String id) {

		this.validateAccessToken(token);
		
		return planEstudioService.getAllPosiblesCursantesMaterias(id);
	}

}
