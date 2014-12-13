package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class JuevesNode implements ProcesadorSemanas {

	private ProcesadorSemanas nextHandler;
	
	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		DiaPlanificacion miercoles = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY);
		DiaPlanificacion jueves = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY);
		
		if (jueves == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (miercoles != null)
					&& (miercoles.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(semana,
						materia);
			} else {
				DiaPlanificacion juevesPlanificacion = new DiaPlanificacion();
				juevesPlanificacion.setDia(Calendar.THURSDAY);
				juevesPlanificacion.setMateria(materia.getMateria());
				juevesPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(juevesPlanificacion);
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