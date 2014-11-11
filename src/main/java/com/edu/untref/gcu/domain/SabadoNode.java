package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class SabadoNode implements ProcesadorNiveles {

	@SuppressWarnings("unused")
	private ProcesadorNiveles nextHandler;
	
	@Override
	public void colocarEnDiaLibre(NivelPlanificacionDTO nivelPlanificacionDTO, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		if (nivelPlanificacionDTO.getSabado() == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (nivelPlanificacionDTO.getViernes() != null)
					&& (nivelPlanificacionDTO.getViernes().getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				throw new NivelCompletoException();
			} else {
				nivelPlanificacionDTO.setSabado(materia);
			}
			
		} else {
			throw new NivelCompletoException();
		}
	}

	@Override
	public void nextHandler(ProcesadorNiveles procesadorNiveles) {
		this.nextHandler = procesadorNiveles;
	}

}