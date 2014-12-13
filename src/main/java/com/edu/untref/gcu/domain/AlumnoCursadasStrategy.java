package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;
import com.edu.untref.gcu.dtos.MateriaDiaDTO;

public class AlumnoCursadasStrategy implements ScoreStrategy {

	@Override
	public List<CursadaAlumnoDTO> processScore(Planificacion planificacion, Paridad paridadSeleccionada) {
		List<CursadaAlumnoDTO> cursadas = new ArrayList<CursadaAlumnoDTO>();

		this.procesarCuatrimestre(planificacion.getCuatrimestre1(), cursadas);
		this.procesarCuatrimestre(planificacion.getCuatrimestre2(), cursadas);
		this.procesarCuatrimestre(planificacion.getCuatrimestre3(), cursadas);
		this.procesarCuatrimestre(planificacion.getCuatrimestre4(), cursadas);
		this.procesarCuatrimestre(planificacion.getCuatrimestre5(), cursadas);
		
		return cursadas;
	}

	@Override
	public void procesarCuatrimestre(List<SemanaPlanificacion> nivelesSemana, List<CursadaAlumnoDTO> cursadas) {
		for (SemanaPlanificacion unNivel : nivelesSemana) {
			this.procesarDia(getDia(unNivel, Calendar.MONDAY), Calendar.MONDAY, cursadas);
			this.procesarDia(getDia(unNivel, Calendar.TUESDAY), Calendar.TUESDAY, cursadas);
			this.procesarDia(getDia(unNivel, Calendar.WEDNESDAY), Calendar.WEDNESDAY, cursadas);
			this.procesarDia(getDia(unNivel, Calendar.THURSDAY), Calendar.THURSDAY, cursadas);
			this.procesarDia(getDia(unNivel, Calendar.FRIDAY), Calendar.FRIDAY, cursadas);
			this.procesarDia(getDia(unNivel, Calendar.SATURDAY), Calendar.SATURDAY, cursadas);
		}
		
	}

	private void procesarDia(DiaPlanificacion diaPlanificacion, int dia, List<CursadaAlumnoDTO> cursadas) {

		if (diaPlanificacion != null) {
			for (Alumno unAlumno : diaPlanificacion.getCursantes()) {
				
				CursadaAlumnoDTO cursadaAlumnoDTO = this.findCursadaByAlumno(unAlumno, cursadas);
				
				if (cursadaAlumnoDTO == null) {
					cursadaAlumnoDTO = new CursadaAlumnoDTO();
					cursadaAlumnoDTO.setAlumno(unAlumno);
					cursadas.add(cursadaAlumnoDTO);
				}
				
				if (cursadaAlumnoDTO.notContainsMateriaDia(dia)
						&& cursadaAlumnoDTO.notContainsMateria(diaPlanificacion.getMateria())) {
					
					MateriaDiaDTO materiaDiaDTO = new MateriaDiaDTO();
					materiaDiaDTO.setDia(dia);
					materiaDiaDTO.setMateria(diaPlanificacion.getMateria());
					cursadaAlumnoDTO.getMaterias().add(materiaDiaDTO);
					
					if (diaPlanificacion.getMateria().getHoras() > 60) {
						MateriaDiaDTO materiaDiaSegundoDiaDTO = new MateriaDiaDTO();
						materiaDiaSegundoDiaDTO.setDia(calcularDia(dia));
						materiaDiaSegundoDiaDTO.setMateria(diaPlanificacion.getMateria());
						cursadaAlumnoDTO.getMaterias().add(materiaDiaSegundoDiaDTO);
					}
				}
				
			}
			
		}
	}

	private int calcularDia(int dia) {
		if ( dia < 5) {
			return dia + 2;
		} else {
			return 1;
		}
	}

	private CursadaAlumnoDTO findCursadaByAlumno(Alumno unAlumno, List<CursadaAlumnoDTO> cursadas) {
		for (CursadaAlumnoDTO unaCursada : cursadas) {
			if (unaCursada.getAlumno().equals(unAlumno)) {
				return unaCursada;
			}
		}

		return null;
	}

	@Override
	public Score calcularScore(int cantidadMaterias, List<CursadaAlumnoDTO> cursadasAlumnos) {
		Score scoreDTO = new Score();
		scoreDTO.setCantidadMaterias(cantidadMaterias);
		
		for (CursadaAlumnoDTO unaCursada: cursadasAlumnos) {
			if (this.sizeMaterias(unaCursada) == cantidadMaterias) {
				scoreDTO.getAlumnos().add(unaCursada.getAlumno());
			}
		}
		
		return scoreDTO;
	}

	private int sizeMaterias(CursadaAlumnoDTO unaCursada) {
		List<PlanMateria> agregadas = new ArrayList<PlanMateria>();
		
		int cantidad = 0;
		
		for (MateriaDiaDTO diaDTO : unaCursada.getMaterias()) {
			if (!agregadas.contains(diaDTO.getMateria())) {
				cantidad ++;
				agregadas.add(diaDTO.getMateria());
			}
		}
		
		return cantidad;
	}

	
	public DiaPlanificacion getDia(SemanaPlanificacion semana, Integer dia) {
		for (DiaPlanificacion unDia: semana.getDias()) {
			if (unDia.getDia().equals(dia)) {
				return unDia;
			}
		}
		return null;
	}
}
