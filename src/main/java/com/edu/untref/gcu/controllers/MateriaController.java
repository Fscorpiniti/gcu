package com.edu.untref.gcu.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.untref.gcu.domain.Materia;
import com.edu.untref.gcu.services.MateriaService;

@Controller
@RequestMapping("/materias")
public class MateriaController {

	@Resource(name = "materiaService")
	private MateriaService materiaService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Materia> getAllMaterias() {
		return materiaService.getAllMaterias();
	}

}