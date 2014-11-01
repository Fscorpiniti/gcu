package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;

public class Dia {
	
	private List<PosiblesCursantesMateriaDTO> posiblesCursantes;

	public Dia (){
		posiblesCursantes = new ArrayList<PosiblesCursantesMateriaDTO>();
	}

	public List<PosiblesCursantesMateriaDTO> getPosiblesCursantes() {
		return posiblesCursantes;
	}
}
