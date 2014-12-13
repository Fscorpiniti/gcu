package com.edu.untref.gcu.domain;

import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

public class ManagerDePlanificacionSemanal {

	private static ProcesadorSemanas startNode;
	
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
	
	public static void colocarEnDiaLibre(SemanaPlanificacion semana, PosiblesCursantesMateriaDTO materia) throws NivelCompletoException {
		startNode.colocarEnDiaLibre(semana, materia);
	}
	
}
