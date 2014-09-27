package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.Materia;
import com.edu.untref.gcu.services.MateriaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/materias") 
@Api(value="materiaController", description="EndPoint que permite realizar acciones sobre las materias.")
public class MateriaController {

	@Resource(name = "materiaService")
	private MateriaService materiaService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Devuelve todas las materias de la carrera ingenieria en computacion.")
	public List<Materia> getAllMaterias() {
		return materiaService.getAllMaterias();
	}

	//TODO FER estos 2 metodos estan de ejemplo para que se realicen los demas endpoints
//	@ResponseBody
//	@RequestMapping(value = "/materias/{id}", method = RequestMethod.GET)
//	public Materia getMateria(@PathVariable String id, @RequestParam String order) {
//		order.toString();
//		return this.materiaService.findById(id);
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/materias", method = RequestMethod.POST)
//	public Materia createMateria(@RequestBody Materia materia) {
//		return this.materiaService.save(materia);
//	}
	
}