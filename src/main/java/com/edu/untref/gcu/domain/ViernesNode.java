package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class ViernesNode implements ProcesadorNiveles {

	private ProcesadorNiveles nextHandler;
	
	@Override
	public void colocarEnDiaLibre(NivelPlanificacionDTO nivelPlanificacionDTO, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		if (nivelPlanificacionDTO.getViernes() == null) {
			if ((materia.getMateria().getHoras() > 60)
					&& (nivelPlanificacionDTO.getJueves() != null)
					&& (nivelPlanificacionDTO.getJueves().getMateria().getId()
							.equals(materia.getMateria().getId()))) {

				this.nextHandler.colocarEnDiaLibre(nivelPlanificacionDTO,
						materia);
			} else {
				nivelPlanificacionDTO.setViernes(materia);
			}
		} else {
			this.nextHandler.colocarEnDiaLibre(nivelPlanificacionDTO, materia);
		}
	}

	@Override
	public void nextHandler(ProcesadorNiveles procesadorNiveles) {
		this.nextHandler = procesadorNiveles;
	}

}