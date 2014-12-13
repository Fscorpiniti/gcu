package com.edu.untref.gcu.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.dtos.ChangePlanificacionDTO;
import com.edu.untref.gcu.dtos.PlanificacionDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;
import com.edu.untref.gcu.services.PlanificacionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/planificacion") 
@Api(value="planificacionController", description="EndPoint que permite calcular la planificacion de cuatrimestres.")
public class PlanificacionController extends MaestroController {
	
	@Resource(name = "planificacionService")
	private PlanificacionService planificacionService;
	
	@ResponseBody
	@RequestMapping(value = "/{paridad}", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve la planificacion del cuatrimestre par o impar de acuerdo a la variable del path. Si se encuentra almacenada, se " +
			"retorna la actual. Sino se genera una nueva por default y se persiste guardando el estado.")
	public PlanificacionDTO getAllMaterias(@RequestParam("access_token") String token, 
			@ApiParam(name = "paridad", required = true) @PathVariable String paridad) throws NivelCompletoException {

		this.validateAccessToken(token);
		
		return planificacionService.planificar("1", paridad.toUpperCase(), Boolean.FALSE);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{paridad}/refresh", method = RequestMethod.GET)
	@ApiOperation(value = "Refrezca la planificacion. Se actualiza la planificacion de la base de datos por la default.")
	public PlanificacionDTO refresh(@RequestParam("access_token") String token, 
			@ApiParam(name = "paridad", required = true) @PathVariable String paridad) throws NivelCompletoException {

		this.validateAccessToken(token);
		
		return planificacionService.planificar("1", paridad.toUpperCase(), Boolean.TRUE);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{paridad}/change", method = RequestMethod.POST)
	@ApiOperation(value = "Cambia de dia una materia en la planificacion actual.")
	public PlanificacionDTO change(@RequestParam("access_token") String token, @ApiParam(name = "paridad", required = true) @PathVariable String paridad, 
			@RequestBody ChangePlanificacionDTO changeDTO) throws NivelCompletoException {

		this.validateAccessToken(token);
		
		return planificacionService.changePlanificacion(paridad.toUpperCase(), changeDTO.getIdMateria(), changeDTO.getNuevoDia());
	}

}
