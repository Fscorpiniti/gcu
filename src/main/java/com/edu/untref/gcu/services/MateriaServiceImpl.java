package com.edu.untref.gcu.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.MateriaDAO;
import com.edu.untref.gcu.domain.Materia;

@Service("materiaService")
public class MateriaServiceImpl implements MateriaService{
	
	@Resource (name = "materiaDAO")
	private MateriaDAO materiaDAO;

	@Override
	public Integer findIdMateriaByCodigo(Integer codigoMateria) {
		return materiaDAO.findIdMateriaByCodigo(codigoMateria);
	}
	
	@Override
	public Materia findMateriaByCodigo(Integer codigoMateria) {
		return materiaDAO.findMateriaByCodigo(codigoMateria);
	}
	

}
