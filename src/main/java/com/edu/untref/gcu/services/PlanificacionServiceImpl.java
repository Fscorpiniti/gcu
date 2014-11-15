package com.edu.untref.gcu.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.AlumnoDAO;
import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.AlumnoCursadasStrategy;
import com.edu.untref.gcu.domain.ManagerDeNiveles;
import com.edu.untref.gcu.domain.Paridad;
import com.edu.untref.gcu.domain.ScoreStrategy;
import com.edu.untref.gcu.dtos.ColisionMateriaComparator;
import com.edu.untref.gcu.dtos.ColisionMateriaDTO;
import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;
import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.dtos.ScoreDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

@Service("planificacionService")
public class PlanificacionServiceImpl implements PlanificacionService {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@Resource(name = "alumnoDAO")
	private AlumnoDAO alumnoDAO;
	
	@Override
	public PlanificacionCuatrimestreDTO planificar(String id, String paridad)
			throws NivelCompletoException {

		Paridad paridadSeleccionada = Paridad.valueOf(paridad);

		List<PosiblesCursantesMateriaDTO> materias = planEstudioService
				.getAllMateriasByCuatrimestre(id, paridadSeleccionada);

		PlanificacionCuatrimestreDTO cuatrimestreDTO = new PlanificacionCuatrimestreDTO();

		ManagerDeNiveles.inicializar();
		
		if (paridadSeleccionada.equals(Paridad.PAR)) {
			cuatrimestreDTO = processParidadPar(materias, cuatrimestreDTO);
		} else {
			cuatrimestreDTO = processParidadImpar(materias, cuatrimestreDTO);
		}

		return cuatrimestreDTO;
	}

	private PlanificacionCuatrimestreDTO processParidadPar(
			List<PosiblesCursantesMateriaDTO> materias,
			PlanificacionCuatrimestreDTO cuatrimestreDTO)
			throws NivelCompletoException {

		List<PosiblesCursantesMateriaDTO> segundoCuatrimestre = this
				.filtrarCuatrimestre(1, 2, materias);
		List<PosiblesCursantesMateriaDTO> cuartoCuatrimestre = this
				.filtrarCuatrimestre(2, 2, materias);
		List<PosiblesCursantesMateriaDTO> sextoCuatrimestre = this
				.filtrarCuatrimestre(3, 2, materias);
		List<PosiblesCursantesMateriaDTO> octavoCuatrimestre = this
				.filtrarCuatrimestre(4, 2, materias);
		List<PosiblesCursantesMateriaDTO> decimoCuatrimestre = this
				.filtrarCuatrimestre(5, 2, materias);

		List<NivelPlanificacionDTO> nivelesSegundoCuatriDTO = procesarNiveles(segundoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesCuartoCuatriDTO = procesarNiveles(cuartoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesSextoCuatriDTO = procesarNiveles(sextoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesOctavoCuatriDTO = procesarNiveles(octavoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesDecimoCuatriDTO = procesarNiveles(decimoCuatrimestre);

		cuatrimestreDTO.getNivelesSegundoCuatri().addAll(
				nivelesSegundoCuatriDTO);
		cuatrimestreDTO.getNivelesCuartoCuatri().addAll(nivelesCuartoCuatriDTO);
		cuatrimestreDTO.getNivelesSextoCuatri().addAll(nivelesSextoCuatriDTO);
		cuatrimestreDTO.getNivelesOctavoCuatri().addAll(nivelesOctavoCuatriDTO);
		cuatrimestreDTO.getNivelesDecimoCuatri().addAll(nivelesDecimoCuatriDTO);
		
		ScoreStrategy strategy = new AlumnoCursadasStrategy();
		List<CursadaAlumnoDTO> cursadasAlumnos = strategy.processScore(cuatrimestreDTO, Paridad.PAR);
		
		cuatrimestreDTO.getScores().add(this.calcularScoreDeCeroMaterias(cursadasAlumnos, this.alumnoDAO.findAll()));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(1, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(2, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(3, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(4, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(5, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(6, cursadasAlumnos));

		return cuatrimestreDTO;
	}

	private ScoreDTO calcularScoreDeCeroMaterias(
			List<CursadaAlumnoDTO> cursadasAlumnos, List<Alumno> findAll) {
		
		List<Alumno> alumnosCursadas = new ArrayList<Alumno>();
		
		for (CursadaAlumnoDTO unaCursada: cursadasAlumnos) {
			alumnosCursadas.add(unaCursada.getAlumno());
		}
		
		ScoreDTO score = new ScoreDTO();
		score.setCantidadMaterias(0);
		score.getAlumnos().addAll(this.differenceList(alumnosCursadas, findAll));
		
		return score;
	}
	
	private List<Alumno> differenceList(List<Alumno> lista1,
			List<Alumno> lista2) {

		List<Alumno> result = new ArrayList<Alumno>();
		
		for (Alumno unAlumno: lista1) {
			if (!lista2.contains(unAlumno)) {
				result.add(unAlumno);
			}
		}
		
		return result;
	}

	private PlanificacionCuatrimestreDTO processParidadImpar(
			List<PosiblesCursantesMateriaDTO> materias,
			PlanificacionCuatrimestreDTO cuatrimestreDTO)
			throws NivelCompletoException {

		List<PosiblesCursantesMateriaDTO> primerCuatrimestre = this
				.filtrarCuatrimestre(1, 1, materias);
		List<PosiblesCursantesMateriaDTO> tercerCuatrimestre = this
				.filtrarCuatrimestre(2, 1, materias);
		List<PosiblesCursantesMateriaDTO> quintoCuatrimestre = this
				.filtrarCuatrimestre(3, 1, materias);
		List<PosiblesCursantesMateriaDTO> septimoCuatrimestre = this
				.filtrarCuatrimestre(4, 1, materias);
		List<PosiblesCursantesMateriaDTO> novenoCuatrimestre = this
				.filtrarCuatrimestre(5, 1, materias);

		List<NivelPlanificacionDTO> nivelesPrimeroCuatriDTO = procesarNiveles(primerCuatrimestre);
		List<NivelPlanificacionDTO> nivelesTerceroCuatriDTO = procesarNiveles(tercerCuatrimestre);
		List<NivelPlanificacionDTO> nivelesQuintoCuatriDTO = procesarNiveles(quintoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesSeptimoCuatriDTO = procesarNiveles(septimoCuatrimestre);
		List<NivelPlanificacionDTO> nivelesNovenoCuatriDTO = procesarNiveles(novenoCuatrimestre);

		cuatrimestreDTO.getNivelesPrimerCuatri()
				.addAll(nivelesPrimeroCuatriDTO);
		cuatrimestreDTO.getNivelesTercerCuatri()
				.addAll(nivelesTerceroCuatriDTO);
		cuatrimestreDTO.getNivelesQuintoCuatri().addAll(nivelesQuintoCuatriDTO);
		cuatrimestreDTO.getNivelesSeptimoCuatri().addAll(
				nivelesSeptimoCuatriDTO);
		cuatrimestreDTO.getNivelesNovenoCuatri().addAll(nivelesNovenoCuatriDTO);

		ScoreStrategy strategy = new AlumnoCursadasStrategy();
		List<CursadaAlumnoDTO> cursadasAlumnos = strategy.processScore(cuatrimestreDTO, Paridad.IMPAR);
		
		cuatrimestreDTO.getScores().add(this.calcularScoreDeCeroMaterias(cursadasAlumnos, this.alumnoDAO.findAll()));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(1, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(2, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(3, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(4, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(5, cursadasAlumnos));
		cuatrimestreDTO.getScores().add(strategy.calcularScore(6, cursadasAlumnos));
		
		return cuatrimestreDTO;
	}

	private List<PosiblesCursantesMateriaDTO> filtrarCuatrimestre(Integer anio,
			Integer cuatrimestre, List<PosiblesCursantesMateriaDTO> materias) {

		List<PosiblesCursantesMateriaDTO> result = new ArrayList<PosiblesCursantesMateriaDTO>();

		for (PosiblesCursantesMateriaDTO dto : materias) {
			if (dto.getMateria().getAnio().equals(anio)
					&& dto.getMateria().getCuatrimestre().equals(cuatrimestre)) {
				result.add(dto);
			}
		}

		return result;
	}

	private List<NivelPlanificacionDTO> procesarNiveles(
			List<PosiblesCursantesMateriaDTO> materiasCuatrimestre)
			throws NivelCompletoException {

		List<NivelPlanificacionDTO> niveles = new ArrayList<NivelPlanificacionDTO>();

		List<ColisionMateriaDTO> colisiones = this
				.procesarColisiones(materiasCuatrimestre);

		Collections.sort(colisiones, new ColisionMateriaComparator());

		NivelPlanificacionDTO nivel1 = new NivelPlanificacionDTO();
		NivelPlanificacionDTO nivel2 = new NivelPlanificacionDTO();

		List<Integer> idsMateriasAgregadas = new ArrayList<Integer>();

		int i;
		for (i = 0; i < colisiones.size(); i++) {
			ColisionMateriaDTO colision = colisiones.get(i);

			colocarEnDiaLibre(nivel1, nivel2, colision.getMateria1(),
					Boolean.FALSE, idsMateriasAgregadas);

			if (colision.getMateria1().getMateria().getHoras() > 60) {
				colocarEnDiaLibre(nivel1, nivel2, colision.getMateria1(),
						Boolean.TRUE, idsMateriasAgregadas);
			}

			colocarEnDiaLibre(nivel1, nivel2, colision.getMateria2(),
					Boolean.FALSE, idsMateriasAgregadas);

			if (colision.getMateria2().getMateria().getHoras() > 60) {
				colocarEnDiaLibre(nivel1, nivel2, colision.getMateria2(),
						Boolean.TRUE, idsMateriasAgregadas);
			}
		}

		niveles.add(nivel1);
		niveles.add(nivel2);

		return niveles;
	}

	private void colocarEnDiaLibre(NivelPlanificacionDTO nivel1,
			NivelPlanificacionDTO nivel2, PosiblesCursantesMateriaDTO materia,
			Boolean dosDias, List<Integer> idsAgregados)
			throws NivelCompletoException {

		if (!idsAgregados.contains(materia.getMateria().getId())
				|| ((materia.getMateria().getHoras() > 60) && (!estaCargadaDosVeces(idsAgregados, materia)))) {
			try {
				ManagerDeNiveles.colocarEnDiaLibre(nivel1, materia);
			} catch (NivelCompletoException nce) {
				ManagerDeNiveles.colocarEnDiaLibre(nivel2, materia);
			}

			idsAgregados.add(materia.getMateria().getId());
		}

	}

	private boolean estaCargadaDosVeces(List<Integer> idsAgregados,
			PosiblesCursantesMateriaDTO materia) {

		Integer vecesCargadas = 0;

		for (Integer unId : idsAgregados) {
			if (unId.equals(materia.getMateria().getId())) {
				vecesCargadas++;
			}
		}

		return vecesCargadas.equals(2);
	}

	private List<ColisionMateriaDTO> procesarColisiones(
			List<PosiblesCursantesMateriaDTO> materiasCuatrimestre) {

		List<ColisionMateriaDTO> result = new ArrayList<ColisionMateriaDTO>();

		int i, j;
		for (i = 0; i < materiasCuatrimestre.size(); i++) {
			PosiblesCursantesMateriaDTO posiblesCursantes1 = materiasCuatrimestre
					.get(i);

			for (j = i + 1; j < materiasCuatrimestre.size(); j++) {
				PosiblesCursantesMateriaDTO posiblesCursantes2 = materiasCuatrimestre
						.get(j);

				ColisionMateriaDTO colisionMateriaDTO = new ColisionMateriaDTO(
						posiblesCursantes1, posiblesCursantes2);

				colisionMateriaDTO.getColisiones().addAll(
						this.interseccion(posiblesCursantes1
								.getAlumnosPosiblesCursantes(),
								posiblesCursantes2
										.getAlumnosPosiblesCursantes()));

				result.add(colisionMateriaDTO);
			}
		}

		return result;
	}

	private Collection<? extends Alumno> interseccion(
			List<Alumno> alumnosPosiblesCursantes,
			List<Alumno> alumnosPosiblesCursantes2) {

		List<Alumno> interseccion = new ArrayList<Alumno>();

		for (Alumno alumno : alumnosPosiblesCursantes) {
			if (alumnosPosiblesCursantes2.contains(alumno)) {
				interseccion.add(alumno);
			}
		}

		return interseccion;
	}

}