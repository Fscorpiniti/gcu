package com.edu.untref.gcu.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
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
	
	@ResponseBody
	@RequestMapping(value = "/{paridad}", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve la planificación del cuatrimestre par o impar de acuerdo a la variable del path.")
	public PlanificacionCuatrimestreDTO getAllMaterias(@ApiParam(name = "paridad", required = true) @PathVariable String paridad) {

		return planificacionService.planificar("1", paridad.toUpperCase());
	}

}
