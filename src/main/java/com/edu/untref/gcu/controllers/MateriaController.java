package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.Materia;
import com.edu.untref.gcu.services.MateriaService;

@Controller
public class MateriaController {

	@Resource(name = "materiaService")
	private MateriaService materiaService;
	
	@ResponseBody
	@RequestMapping(value = "/materias",method = RequestMethod.GET)
	public List<Materia> getAllMaterias() {
		return materiaService.getAllMaterias();
	}

	@ResponseBody
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.GET)
	public Materia getMateria(@PathVariable String id, @RequestParam String order) {
		order.toString();
		return this.materiaService.findById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/materias", method = RequestMethod.POST)
	public Materia createMateria(@RequestBody Materia materia) {
		return this.materiaService.save(materia);
	}
	
}