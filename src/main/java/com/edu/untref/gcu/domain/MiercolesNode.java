package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class MiercolesNode implements ProcesadorSemanas {

	private ProcesadorSemanas nextHandler;
	
	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		DiaPlanificacion martes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY);
		DiaPlanificacion miercoles = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY);
		
		if (miercoles == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (martes != null)
					&& (martes.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(semana, materia);
			} else {
				DiaPlanificacion miercolesPlanificacion = new DiaPlanificacion();
				miercolesPlanificacion.setDia(Calendar.WEDNESDAY);
				miercolesPlanificacion.setMateria(materia.getMateria());
				miercolesPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(miercolesPlanificacion);
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