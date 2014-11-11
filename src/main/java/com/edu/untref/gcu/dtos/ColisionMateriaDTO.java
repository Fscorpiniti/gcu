package com.edu.untref.gcu.dtos;

import java.util.ArrayList;
import java.util.List;

import com.edu.untref.gcu.domain.Alumno;

public class ColisionMateriaDTO {
	
	private PosiblesCursantesMateriaDTO materia1;
	
	private PosiblesCursantesMateriaDTO materia2;
	
	private List<Alumno> colisiones = new ArrayList<Alumno>();

	public ColisionMateriaDTO(PosiblesCursantesMateriaDTO materia1, PosiblesCursantesMateriaDTO materia2) {
		this.setMateria1(materia1);
		this.setMateria2(materia2);
	}

	public List<Alumno> getColisiones() {
		return colisiones;
	}

	public void setColisiones(List<Alumno> colisiones) {
		this.colisiones = colisiones;
	}

	public PosiblesCursantesMateriaDTO getMateria1() {
		return materia1;
	}

	public void setMateria1(PosiblesCursantesMateriaDTO materia1) {
		this.materia1 = materia1;
	}

	public PosiblesCursantesMateriaDTO getMateria2() {
		return materia2;
	}

	public void setMateria2(PosiblesCursantesMateriaDTO materia2) {
		this.materia2 = materia2;
	}
	
}
