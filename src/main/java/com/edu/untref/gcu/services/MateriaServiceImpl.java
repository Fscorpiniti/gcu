package com.edu.untref.gcu.services;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.MateriaDAO;
import com.edu.untref.gcu.domain.Materia;

@Transactional
@Service("materiaService")
public class MateriaServiceImpl implements MateriaService {

	@Resource(name = "materiaDAO")
	private MateriaDAO materiaDAO;

	@Override
	public List<Materia> getAllMaterias() {
		return materiaDAO.findAll();
	}

	@Override
	public Materia findById(String id) {
		return this.materiaDAO.findById(id);
	}

	@Override
	public Materia save(Materia materia) {
		this.materiaDAO.save(materia);
		
		return materia;
	}
	
}
