package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class LunesNode implements ProcesadorSemanas {

	private ProcesadorSemanas nextHandler;

	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		DiaPlanificacion lunes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY);
		DiaPlanificacion martes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY);
		
		if (lunes == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (martes != null)
					&& (martes.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(semana, materia);
			} else {
				DiaPlanificacion lunesPlanificacion = new DiaPlanificacion();
				lunesPlanificacion.setDia(Calendar.MONDAY);
				lunesPlanificacion.setMateria(materia.getMateria());
				lunesPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(lunesPlanificacion);
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
