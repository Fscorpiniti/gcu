package com.edu.untref.gcu.services;

import com.edu.untref.gcu.domain.Alumno;

public interface AlumnoService {
	 void save(Alumno alumno);
	 Alumno findByLejago(Integer legajo);
}
