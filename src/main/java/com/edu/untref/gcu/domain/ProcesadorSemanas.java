package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public interface ProcesadorSemanas {

	void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException;

	void nextHandler(ProcesadorSemanas procesadorNiveles);
	
}
