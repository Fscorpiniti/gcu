package com.edu.untref.gcu.domain;

import java.util.Calendar;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class ViernesNode implements ProcesadorSemanas {

	private ProcesadorSemanas nextHandler;
	
	@Override
	public void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		DiaPlanificacion viernes = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY);
		DiaPlanificacion jueves = ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY);
		
		if (viernes == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (jueves != null)
					&& (jueves.getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(semana,
						materia);
			} else {
				DiaPlanificacion viernesPlanificacion = new DiaPlanificacion();
				viernesPlanificacion.setDia(Calendar.FRIDAY);
				viernesPlanificacion.setMateria(materia.getMateria());
				viernesPlanificacion.setCursantes(materia.getAlumnosPosiblesCursantes());
				semana.getDias().add(viernesPlanificacion);
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