package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.services.PlanificacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/planificacion") 
@Api(value="planificacionController", description="EndPoint que permite calcular la planificacion de cuatrimestres.")
public class PlanificacionController {
	
	@Resource(name = "planificacionService")
	private PlanificacionService planificacionService;
	
//	@ResponseBody
//	@RequestMapping(value = "/{paridad}/planificacion", method = RequestMethod.GET)
//	@ApiOperation(value = "Devuelve la planificación semanal de las materias del cuatrimestre seleccionado.")
//	public Semana getAllMaterias(
//			@ApiParam(name = "paridad", required = true) @PathVariable String paridad) {
//
//		return planificacionService.getPlanificacionPorParidad("1", paridad);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/{anio}/{cuatrimestre}", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve la planificacion del numero de cuatrimestre pasado por parametros del plan 2008 de la carrera Ingenieria en computacion.")
	public List<NivelPlanificacionDTO> planificarCuatrimestre(
			@ApiParam(name = "anio", required = true) @PathVariable String anio, 
			@ApiParam(name = "cuatrimestre", required = true) @PathVariable String cuatrimestre) {

		return planificacionService.planificar("1", anio, cuatrimestre);
	}

}
