package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public interface ProcesadorNiveles {

	void colocarEnDiaLibre(NivelPlanificacionDTO nivelPlanificacionDTO, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException;

	void nextHandler(ProcesadorNiveles procesadorNiveles);
	
}
