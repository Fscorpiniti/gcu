package com.edu.untref.gcu.domain;


public class Semana {
	
	private Dia lunes;
	private Dia martes;
	private Dia miercoles;
	private Dia jueves;
	private Dia viernes;
	private Dia sabado;

	public Semana (){

		lunes = new Dia();
		martes = new Dia();
		miercoles = new Dia();
		jueves = new Dia();
		viernes = new Dia();
		sabado = new Dia();
		
	}

	public Dia getLunes() {
		return lunes;
	}

	public Dia getMartes() {
		return martes;
	}

	public Dia getMiercoles() {
		return miercoles;
	}

	public Dia getJueves() {
		return jueves;
	}

	public Dia getViernes() {
		return viernes;
	}

	public Dia getSabado() {
		return sabado;
	}

}
