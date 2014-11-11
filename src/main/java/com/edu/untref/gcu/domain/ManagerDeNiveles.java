package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class ManagerDeNiveles {

	private static ProcesadorNiveles startNode;
	
	public static void inicializar() {
		startNode = new LunesNode();
		
		MartesNode martesNode = new MartesNode();
		startNode.nextHandler(martesNode);
		
		MiercolesNode miercolesNode = new MiercolesNode();
		martesNode.nextHandler(miercolesNode);
		
		JuevesNode juevesNode = new JuevesNode();
		miercolesNode.nextHandler(juevesNode);
		
		ViernesNode viernesNode = new ViernesNode();
		juevesNode.nextHandler(viernesNode);
		
		SabadoNode sabadoNode = new SabadoNode();
		viernesNode.nextHandler(sabadoNode);
	}
	
	public static void colocarEnDiaLibre(NivelPlanificacionDTO nivelPlanificacionDTO, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		startNode.colocarEnDiaLibre(nivelPlanificacionDTO, materia);
	}
	
}
