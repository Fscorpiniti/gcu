package com.edu.untref.gcu.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.MateriaDAO;

@Service("materiaService")
public class MateriaServiceImpl implements MateriaService{
	
	@Resource (name = "materiaDAO")
	private MateriaDAO materiaDAO;

	@Override
	public Integer findIdMateriaByNombre(String nombreMateria) {
		return materiaDAO.findIdMateriaByNombre(nombreMateria);
	}
	
	

}
