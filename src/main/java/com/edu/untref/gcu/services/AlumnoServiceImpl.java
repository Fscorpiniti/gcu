package com.edu.untref.gcu.services;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.AlumnoDAO;
import com.edu.untref.gcu.domain.Alumno;

@Transactional
@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService{
	
	@Resource(name = "alumnoDAO")
	private AlumnoDAO alumnoDAO;
	
	public void save(Alumno alumno){
		alumnoDAO.save(alumno);
	}

	@Override
	public Alumno findByLejago(Integer legajo) {
		return alumnoDAO.findByLejago(legajo);
	}
}
