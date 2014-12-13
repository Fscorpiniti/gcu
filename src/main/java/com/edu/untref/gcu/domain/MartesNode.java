package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class MartesNode implements ProcesadorSemanas {

	private ProcesadorSemanas nextHandler;
	
	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {	
		DiaPlanificacion lunes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY);
		DiaPlanificacion martes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY);
		
		if (martes == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (lunes != null)
					&& (lunes.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(semana, materia);
			} else {
				DiaPlanificacion martesPlanificacion = new DiaPlanificacion();
				martesPlanificacion.setDia(Calendar.TUESDAY);
				martesPlanificacion.setMateria(materia.getMateria());
				martesPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(martesPlanificacion);
			}
		} else {
			this.nextHandler.colocarEnDiaLibre(semana, materia);
		}
	}

	@Override
	public void nextHandler(ProcesadorSemanas procesadorNiveles) {
		this.nextHandler = procesadorNiveles;
	}

}