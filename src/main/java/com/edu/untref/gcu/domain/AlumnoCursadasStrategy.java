package com.edu.untref.gcu.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;
import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.dtos.ScoreDTO;

public class AlumnoCursadasStrategy implements ScoreStrategy {

	@Override
	public List<CursadaAlumnoDTO> processScore(
			PlanificacionCuatrimestreDTO cuatrimestreDTO, Paridad paridadSeleccionada) {

		List<CursadaAlumnoDTO> cursadas = new ArrayList<CursadaAlumnoDTO>();

		if (paridadSeleccionada.equals(Paridad.PAR)) {
			this.procesarNiveles(cuatrimestreDTO.getNivelesSegundoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesCuartoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesSextoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesOctavoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesDecimoCuatri(), cursadas);
		} else {
			this.procesarNiveles(cuatrimestreDTO.getNivelesPrimerCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesTercerCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesQuintoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesSeptimoCuatri(), cursadas);
			this.procesarNiveles(cuatrimestreDTO.getNivelesNovenoCuatri(), cursadas);
		}
		
		return cursadas;
	}

	private void procesarNiveles(List<NivelPlanificacionDTO> niveles, List<CursadaAlumnoDTO> cursadas) {
		for (NivelPlanificacionDTO unNivel : niveles) {
			this.procesarDia(unNivel.getLunes(), Calendar.MONDAY, cursadas);
			this.procesarDia(unNivel.getMartes(), Calendar.TUESDAY, cursadas);
			this.procesarDia(unNivel.getMiercoles(), Calendar.WEDNESDAY, cursadas);
			this.procesarDia(unNivel.getJueves(), Calendar.THURSDAY, cursadas);
			this.procesarDia(unNivel.getViernes(), Calendar.FRIDAY, cursadas);
			this.procesarDia(unNivel.getSabado(), Calendar.SATURDAY, cursadas);
		}
		
	}

	private void procesarDia(PosiblesCursantesMateriaDTO posiblesCursantesDia,
			int dia, List<CursadaAlumnoDTO> cursadas) {

		if (posiblesCursantesDia != null) {
			for (Alumno unAlumno : posiblesCursantesDia
					.getAlumnosPosiblesCursantes()) {
				
				CursadaAlumnoDTO cursadaAlumnoDTO = this.findCursadaByAlumno(
						unAlumno, cursadas);
				
				if (cursadaAlumnoDTO == null) {
					cursadaAlumnoDTO = new CursadaAlumnoDTO();
					cursadaAlumnoDTO.setAlumno(unAlumno);
					cursadas.add(cursadaAlumnoDTO);
				}
				
				if (cursadaAlumnoDTO.notContainsMateriaDia(dia)
						&& cursadaAlumnoDTO.notContainsMateria(posiblesCursantesDia
								.getMateria())) {
					
					MateriaDiaDTO materiaDiaDTO = new MateriaDiaDTO();
					materiaDiaDTO.setDia(dia);
					materiaDiaDTO.setMateria(posiblesCursantesDia.getMateria());
					cursadaAlumnoDTO.getMaterias().add(materiaDiaDTO);
					
					if (posiblesCursantesDia.getMateria().getHoras() > 60) {
						MateriaDiaDTO materiaDiaSegundoDiaDTO = new MateriaDiaDTO();
						materiaDiaSegundoDiaDTO.setDia(calcularDia(dia));
						materiaDiaSegundoDiaDTO.setMateria(posiblesCursantesDia.getMateria());
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

	private CursadaAlumnoDTO findCursadaByAlumno(Alumno unAlumno,
			List<CursadaAlumnoDTO> cursadas) {

		for (CursadaAlumnoDTO unaCursada : cursadas) {
			if (unaCursada.getAlumno().equals(unAlumno)) {
				return unaCursada;
			}
		}

		return null;
	}

	@Override
	public ScoreDTO calcularScore(int cantidadMaterias,
			List<CursadaAlumnoDTO> cursadasAlumnos) {
		
		ScoreDTO scoreDTO = new ScoreDTO();
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

}
