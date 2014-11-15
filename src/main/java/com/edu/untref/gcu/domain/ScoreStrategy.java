package com.edu.untref.gcu.domain;

import java.util.List;

import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;
import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
import com.edu.untref.gcu.dtos.ScoreDTO;

public interface ScoreStrategy {

	List<CursadaAlumnoDTO> processScore(PlanificacionCuatrimestreDTO cuatrimestreDTO, Paridad par);

	ScoreDTO calcularScore(int cantidadMaterias, List<CursadaAlumnoDTO> cursadasAlumnos);
	
}
