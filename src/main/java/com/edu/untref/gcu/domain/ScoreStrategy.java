package com.edu.untref.gcu.domain;

import java.util.List;

import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;

public interface ScoreStrategy {

	/**
	 * Procesa todos los cuatrimestres de la paridad pasada por parametros
	 * 
	 * @param cuatrimestreDTO
	 * @param par
	 * @return List<CursadaAlumnoDTO>
	 * 
	 */
	List<CursadaAlumnoDTO> processScore(Planificacion planificacion, Paridad par);

	/**
	 * Procesa solo los niveles pasados por parametros, y carga en la lista de cursadas las que corresponden a ese cuatrimestre
	 * 
	 * @param niveles
	 * @param cursadas
	 * 
	 */
	void procesarCuatrimestre(List<SemanaPlanificacion> nivelesSemana, List<CursadaAlumnoDTO> cursadas);
	
	/**
	 * Calcula el Score de una determinada cantidad de materias
	 * 
	 * @param cantidadMaterias
	 * @param cursadasAlumnos
	 * 
	 * @return ScoreDTO
	 */
	Score calcularScore(int cantidadMaterias, List<CursadaAlumnoDTO> cursadasAlumnos);
	
}
