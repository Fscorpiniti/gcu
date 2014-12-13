package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class SabadoNode implements ProcesadorSemanas {

	@SuppressWarnings("unused")
	private ProcesadorSemanas nextHandler;
	
	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		DiaPlanificacion viernes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY);
		DiaPlanificacion sabado = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY);
		
		if (sabado == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (viernes != null)
					&& (viernes.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				throw new NivelCompletoException();
			} else {
				DiaPlanificacion sabadoPlanificacion = new DiaPlanificacion();
				sabadoPlanificacion.setDia(Calendar.SATURDAY);
				sabadoPlanificacion.setMateria(materia.getMateria());
				sabadoPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(sabadoPlanificacion);
			}
			
		} else {
			throw new NivelCompletoException();
		}
	}

	@Override
	public void nextHandler(ProcesadorSemanas procesadorNiveles) {
		this.nextHandler = procesadorNiveles;
	}

}