package com.edu.untref.gcu.domain;

public class ValidadorDisponibilidad {

	public static DiaPlanificacion validarDisponibilidad(SemanaPlanificacion semana, Integer dia) {
		for (DiaPlanificacion unDia: semana.getDias()) {
			if (unDia.getDia().equals(dia)) {
				return unDia;
			}
		}
		return null;
	}
	
}
