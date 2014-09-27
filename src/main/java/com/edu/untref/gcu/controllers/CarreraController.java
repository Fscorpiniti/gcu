package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.PlanEstudio;
import com.edu.untref.gcu.services.CarreraService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/carreras") 
@Api(value="carrerasController", description="EndPoint que permite realizar acciones sobre las carreras.")
public class CarreraController {

	@Resource(name = "carreraService")
	private CarreraService carreraService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/planes", method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve todos los planes de estudio de una carrera determinada.")
	public List<PlanEstudio> getAllPlanes(
			@ApiParam (name = "id", required = true) @PathVariable String id) {
		return carreraService.getAllPlanesByCarrera(Integer.valueOf(id));
	}
}
