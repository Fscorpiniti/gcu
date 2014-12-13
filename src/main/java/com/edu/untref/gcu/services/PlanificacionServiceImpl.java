package com.edu.untref.gcu.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.AlumnoDAO;
import com.edu.untref.gcu.daos.DiaPlanificacionDAO;
import com.edu.untref.gcu.daos.PlanificacionDAO;
import com.edu.untref.gcu.daos.ScoreDAO;
import com.edu.untref.gcu.daos.SemanaPlanificacionDAO;
import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.AlumnoCursadasStrategy;
import com.edu.untref.gcu.domain.ColisionMateriaComparator;
import com.edu.untref.gcu.domain.DiaPlanificacion;
import com.edu.untref.gcu.domain.ManagerDePlanificacionSemanal;
import com.edu.untref.gcu.domain.Materia;
import com.edu.untref.gcu.domain.Paridad;
import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.domain.Planificacion;
import com.edu.untref.gcu.domain.Score;
import com.edu.untref.gcu.domain.ScoreStrategy;
import com.edu.untref.gcu.domain.SemanaPlanificacion;
import com.edu.untref.gcu.domain.ValidadorDisponibilidad;
import com.edu.untref.gcu.dtos.ColisionMateriaDTO;
import com.edu.untref.gcu.dtos.CursadaAlumnoDTO;
import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PlanificacionDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;
import com.edu.untref.gcu.exceptions.NivelCompletoException;

@Transactional
@Service("planificacionService")
public class PlanificacionServiceImpl implements PlanificacionService {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@Resource(name = "alumnoDAO")
	private AlumnoDAO alumnoDAO;
	
	@Resource(name = "planificacionDAO")
	private PlanificacionDAO planificacionDAO;
	
	@Resource(name = "semanaPlanificacionDAO")
	private SemanaPlanificacionDAO semanaPlanificacionDAO;
	
	@Resource(name = "diaPlanificacionDAO")
	private DiaPlanificacionDAO diaPlanificacionDAO;
	
	@Resource(name = "scoreDAO")
	private ScoreDAO scoreDAO;
	
	@Resource(name = "planMateriaService")
	private PlanMateriaService planMateriaService;
	
	@Resource(name = "materiaService")
	private MateriaService materiaService;
	
	@Override
	public PlanificacionDTO planificar(String id, String paridad, Boolean refresh) throws NivelCompletoException {

		Paridad paridadSeleccionada = Paridad.valueOf(paridad);

		List<PosiblesCursantesMateriaDTO> materias = planEstudioService
				.getAllMateriasByCuatrimestre(id, paridadSeleccionada);

		Planificacion planificacion = this.planificacionDAO.findByParidad(paridad);
		
		if (refresh && (planificacion != null)) {
			this.removePlanificacion(planificacion);
			planificacion = null;
		}
		
		if (planificacion == null) {
			planificacion = new Planificacion();
			
			ManagerDePlanificacionSemanal.inicializar();
			
			if (paridadSeleccionada.equals(Paridad.PAR)) {
				planificacion = processParidadPar(materias);
			} else {
				planificacion = processParidadImpar(materias);
			}
			
			planificacion.setFechaActualizacion(new Date());
			
			savePlanificacion(planificacion);
		}
		
		PlanificacionDTO planificacionDTO = this.convertPlanificacionDTO(planificacion);
		
		return planificacionDTO;
	}
	
	private PlanificacionDTO convertPlanificacionDTO(Planificacion planificacion) {
		PlanificacionDTO planificacionDTO = new PlanificacionDTO();
		planificacionDTO.setParidad(planificacion.getParidad());
		planificacionDTO.setScore(planificacion.getScore());
		planificacionDTO.setScoreCuatrimestre1(planificacion.getScoreCuatrimestre1());
		planificacionDTO.setScoreCuatrimestre2(planificacion.getScoreCuatrimestre2());
		planificacionDTO.setScoreCuatrimestre3(planificacion.getScoreCuatrimestre3());
		planificacionDTO.setScoreCuatrimestre4(planificacion.getScoreCuatrimestre4());
		planificacionDTO.setScoreCuatrimestre5(planificacion.getScoreCuatrimestre5());
		planificacionDTO.setScores(planificacion.getScores());
		
		String patron = "dd/MM/yyyy";
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		String fecha = formato.format(planificacion.getFechaActualizacion());
		planificacionDTO.setFechaActualizacion(fecha);
		
		convertToNiveles(planificacion, planificacionDTO);
		
		return planificacionDTO;
	}

	private void convertToNiveles(Planificacion planificacion, PlanificacionDTO planificacionDTO) {
		
		for (SemanaPlanificacion semana: planificacion.getCuatrimestre1()) {
			NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
			nivelPlanificacionDTO.setLunes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY));
			nivelPlanificacionDTO.setMartes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY));
			nivelPlanificacionDTO.setMiercoles(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY));
			nivelPlanificacionDTO.setJueves(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY));
			nivelPlanificacionDTO.setViernes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY));
			nivelPlanificacionDTO.setSabado(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY));
			
			planificacionDTO.getCuatrimestre1().add(nivelPlanificacionDTO);
		}
		
		for (SemanaPlanificacion semana: planificacion.getCuatrimestre2()) {
			NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
			nivelPlanificacionDTO.setLunes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY));
			nivelPlanificacionDTO.setMartes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY));
			nivelPlanificacionDTO.setMiercoles(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY));
			nivelPlanificacionDTO.setJueves(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY));
			nivelPlanificacionDTO.setViernes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY));
			nivelPlanificacionDTO.setSabado(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY));
			
			planificacionDTO.getCuatrimestre2().add(nivelPlanificacionDTO);
		}
		
		for (SemanaPlanificacion semana: planificacion.getCuatrimestre3()) {
			NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
			nivelPlanificacionDTO.setLunes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY));
			nivelPlanificacionDTO.setMartes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY));
			nivelPlanificacionDTO.setMiercoles(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY));
			nivelPlanificacionDTO.setJueves(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY));
			nivelPlanificacionDTO.setViernes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY));
			nivelPlanificacionDTO.setSabado(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY));
			
			planificacionDTO.getCuatrimestre3().add(nivelPlanificacionDTO);
		}
		
		for (SemanaPlanificacion semana: planificacion.getCuatrimestre4()) {
			NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
			nivelPlanificacionDTO.setLunes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY));
			nivelPlanificacionDTO.setMartes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY));
			nivelPlanificacionDTO.setMiercoles(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY));
			nivelPlanificacionDTO.setJueves(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY));
			nivelPlanificacionDTO.setViernes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY));
			nivelPlanificacionDTO.setSabado(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY));
			
			planificacionDTO.getCuatrimestre4().add(nivelPlanificacionDTO);
		}
		
		for (SemanaPlanificacion semana: planificacion.getCuatrimestre5()) {
			NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
			nivelPlanificacionDTO.setLunes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.MONDAY));
			nivelPlanificacionDTO.setMartes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.TUESDAY));
			nivelPlanificacionDTO.setMiercoles(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.WEDNESDAY));
			nivelPlanificacionDTO.setJueves(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.THURSDAY));
			nivelPlanificacionDTO.setViernes(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.FRIDAY));
			nivelPlanificacionDTO.setSabado(ValidadorDisponibilidad.validarDisponibilidad(semana, Calendar.SATURDAY));
			
			planificacionDTO.getCuatrimestre5().add(nivelPlanificacionDTO);
		}
	}

	@Override
	public PlanificacionDTO changePlanificacion(String paridad, Integer codigoMateria, String dia) {
		
		Paridad paridadSeleccionada = Paridad.valueOf(paridad);
		
		Planificacion planificacion = this.planificacionDAO.findByParidad(paridad);
		
		Materia materia = materiaService.findMateriaByCodigo(codigoMateria);
		PlanMateria planMateria = planMateriaService.findPlanMateriaByIdMateria(materia.getId());
		
		String[] splitNuevoDia = dia.split(",");
		
		if (planMateria.getHoras() > 60) {
			List<DiaPlanificacion> diasAntiguos = findDiasByMateriaCuatrimestre1(planificacion, codigoMateria);
			String nuevoDia1 = splitNuevoDia[0].trim();
			String nuevoDia2 = splitNuevoDia[1].trim();
			
			this.addMateriaChange(diasAntiguos, planificacion, Integer.valueOf(nuevoDia1), Integer.valueOf(nuevoDia2));
		} else {
			DiaPlanificacion diaAntiguo = findDiaByMateriaCuatrimestre1(planificacion, codigoMateria);
			String nuevoDia = splitNuevoDia[0];
			
			this.addMateriaChange(diaAntiguo, planificacion, Integer.valueOf(nuevoDia));
		}
		
		ScoreStrategy strategy = new AlumnoCursadasStrategy();
		List<CursadaAlumnoDTO> cursadasAlumnos = strategy.processScore(planificacion, paridadSeleccionada);
		
		this.calcularScoreTipoCuatrimestre(planificacion, strategy, cursadasAlumnos);
		
		planificacion.setScore(this.calcularScoreUnificado(planificacion.getScores()));
		
		this.calcularScoreCuatrimestres(planificacion, strategy);
		
		planificacion.setFechaActualizacion(new Date());
		
		savePlanificacion(planificacion);
		
		PlanificacionDTO planificacionDTO = this.convertPlanificacionDTO(planificacion);
		
		return planificacionDTO;
	}
	
	private void addMateriaChange(List<DiaPlanificacion> diasAntiguos,
			Planificacion planificacion, Integer nuevoDia1, Integer nuevoDia2) {
		DiaPlanificacion diaAntiguo1 = diasAntiguos.get(0);
		DiaPlanificacion diaAntiguo2 = diasAntiguos.get(0);
		
		addMateria(diaAntiguo1, planificacion, nuevoDia1, planificacion.getCuatrimestre1());
		addMateria(diaAntiguo2, planificacion, nuevoDia2, planificacion.getCuatrimestre1());
		
		addMateria(diaAntiguo1, planificacion, nuevoDia1, planificacion.getCuatrimestre2());
		addMateria(diaAntiguo2, planificacion, nuevoDia2, planificacion.getCuatrimestre2());
		
		addMateria(diaAntiguo1, planificacion, nuevoDia1, planificacion.getCuatrimestre3());
		addMateria(diaAntiguo2, planificacion, nuevoDia2, planificacion.getCuatrimestre3());
		
		addMateria(diaAntiguo1, planificacion, nuevoDia1, planificacion.getCuatrimestre4());
		addMateria(diaAntiguo2, planificacion, nuevoDia2, planificacion.getCuatrimestre4());
		
		addMateria(diaAntiguo1, planificacion, nuevoDia1, planificacion.getCuatrimestre5());
		addMateria(diaAntiguo2, planificacion, nuevoDia2, planificacion.getCuatrimestre5());
		
	}

	private List<DiaPlanificacion> findDiasByMateriaCuatrimestre1(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo1 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre1());
		DiaPlanificacion diaAntiguo2 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre1());
		
		if (diaAntiguo1 == null && diaAntiguo2 == null) {
			return findDiasByMateriaCuatrimestre2(planificacion, codigoMateria);
		} else {
			return Arrays.asList(diaAntiguo1, diaAntiguo2);
		}
	}
	
	private List<DiaPlanificacion> findDiasByMateriaCuatrimestre2(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo1 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre2());
		DiaPlanificacion diaAntiguo2 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre2());
		
		if (diaAntiguo1 == null && diaAntiguo2 == null) {
			return findDiasByMateriaCuatrimestre3(planificacion, codigoMateria);
		} else {
			return Arrays.asList(diaAntiguo1, diaAntiguo2);
		}
	}
	
	private List<DiaPlanificacion> findDiasByMateriaCuatrimestre3(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo1 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre3());
		DiaPlanificacion diaAntiguo2 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre3());
		
		if (diaAntiguo1 == null && diaAntiguo2 == null) {
			return findDiasByMateriaCuatrimestre4(planificacion, codigoMateria);
		} else {
			return Arrays.asList(diaAntiguo1, diaAntiguo2);
		}
	}
	
	private List<DiaPlanificacion> findDiasByMateriaCuatrimestre4(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo1 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre4());
		DiaPlanificacion diaAntiguo2 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre4());
		
		if (diaAntiguo1 == null && diaAntiguo2 == null) {
			return findDiasByMateriaCuatrimestre5(planificacion, codigoMateria);
		} else {
			return Arrays.asList(diaAntiguo1, diaAntiguo2);
		}
	}
	
	private List<DiaPlanificacion> findDiasByMateriaCuatrimestre5(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo1 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre5());
		DiaPlanificacion diaAntiguo2 = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre5());
		
		if (diaAntiguo1 == null && diaAntiguo2 == null) {
			return null;
		} else {
			return Arrays.asList(diaAntiguo1, diaAntiguo2);
		}
	}

	private DiaPlanificacion findDiaByMateriaCuatrimestre1(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre1());
		if (diaAntiguo == null) {
			return findDiaByMateriaCuatrimestre2(planificacion, codigoMateria);
		} else {
			return diaAntiguo;
		}
	}
	
	private DiaPlanificacion findDiaByMateriaCuatrimestre2(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre2());
		if (diaAntiguo == null) {
			return findDiaByMateriaCuatrimestre3(planificacion, codigoMateria);
		} else {
			return diaAntiguo;
		}
	}
	
	private DiaPlanificacion findDiaByMateriaCuatrimestre3(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre3());
		if (diaAntiguo == null) {
			return findDiaByMateriaCuatrimestre4(planificacion, codigoMateria);
		} else {
			return diaAntiguo;
		}
	}
	
	private DiaPlanificacion findDiaByMateriaCuatrimestre4(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre4());
		if (diaAntiguo == null) {
			return findDiaByMateriaCuatrimestre5(planificacion, codigoMateria);
		} else {
			return diaAntiguo;
		}
	}
	
	private DiaPlanificacion findDiaByMateriaCuatrimestre5(Planificacion planificacion, Integer codigoMateria) {
		DiaPlanificacion diaAntiguo = this.removeMateriaChange(codigoMateria, planificacion.getCuatrimestre5());
		if (diaAntiguo == null) {
			return null;
		} else {
			return diaAntiguo;
		}
	}

	private void addMateriaChange(DiaPlanificacion diaAntiguo, Planificacion planificacion, Integer nuevoDia) {

		addMateria(diaAntiguo, planificacion, nuevoDia, planificacion.getCuatrimestre1());
		addMateria(diaAntiguo, planificacion, nuevoDia, planificacion.getCuatrimestre2());
		addMateria(diaAntiguo, planificacion, nuevoDia, planificacion.getCuatrimestre3());
		addMateria(diaAntiguo, planificacion, nuevoDia, planificacion.getCuatrimestre4());
		addMateria(diaAntiguo, planificacion, nuevoDia, planificacion.getCuatrimestre5());
		
	}

	private void addMateria(DiaPlanificacion diaAntiguo, Planificacion planificacion, Integer nuevoDia, List<SemanaPlanificacion> semanas) {
		List<SemanaPlanificacion> nuevasSemanas = new ArrayList<SemanaPlanificacion>();
		Boolean agregada = false;
		int i = 0;
		
		for (SemanaPlanificacion unaSemana: semanas) {
			i ++;
			
			Iterator<DiaPlanificacion> iterador = unaSemana.getDias().iterator();
			if (iterador.hasNext() && !agregada) {
				DiaPlanificacion unDia = iterador.next();
				
				if (unDia.getMateria().getAnio().equals(diaAntiguo.getMateria().getAnio()) && 
						unDia.getMateria().getCuatrimestre().equals(diaAntiguo.getMateria().getCuatrimestre())) {
					
					DiaPlanificacion dia = ValidadorDisponibilidad.validarDisponibilidad(unaSemana, nuevoDia);
					
					DiaPlanificacion diaPlanificacion = new DiaPlanificacion();
					diaPlanificacion.setMateria(diaAntiguo.getMateria());
					diaPlanificacion.setDia(nuevoDia);
					diaPlanificacion.getCursantes().addAll(diaAntiguo.getCursantes());
					
					if (dia == null) {	
						unaSemana.getDias().add(diaPlanificacion);
						agregada = true;
					} else {
						if (i == semanas.size()) {
							SemanaPlanificacion nuevaSemana = new SemanaPlanificacion();
							nuevaSemana.getDias().add(diaPlanificacion);
							nuevasSemanas.add(nuevaSemana);
							agregada = true;
						}
					}
					
				}
				
			}
		}
		
		if (nuevasSemanas.size() > 0 ) {
			semanas.addAll(nuevasSemanas);
		}
	}

	private DiaPlanificacion removeMateriaChange(Integer codigoMateria, List<SemanaPlanificacion> semanas) {
		DiaPlanificacion diaAntiguo = null;
		Boolean removeSemana = false;
		int indiceSemana = -1;
		int j = -1;
		
		for (SemanaPlanificacion unaSemana: semanas) {
			j ++;
			int i = -1;
			int indice = -1;
			for (DiaPlanificacion diaPlanificacion: unaSemana.getDias()) {
				i++;
				if (diaPlanificacion.getMateria().getMateria().getCodigo().equals(codigoMateria)) {
					diaAntiguo = diaPlanificacion;
					indice = i;
				}
			}
			
			if (indice != -1) {
				unaSemana.getDias().remove(indice);
				if (unaSemana.getDias().size() == 0) {
					removeSemana = true;
					indiceSemana = j;
				}
			}
		}
		
		if (removeSemana) {
			semanas.remove(indiceSemana);
		}
		
		return diaAntiguo;
	}

	private void removePlanificacion(Planificacion planificacion) {
		removeSemanaPlanificacion(planificacion.getCuatrimestre1());
		removeSemanaPlanificacion(planificacion.getCuatrimestre2());
		removeSemanaPlanificacion(planificacion.getCuatrimestre3());
		removeSemanaPlanificacion(planificacion.getCuatrimestre4());
		removeSemanaPlanificacion(planificacion.getCuatrimestre5());
		
		this.planificacionDAO.remove(planificacion.getId());
	}

	private void removeSemanaPlanificacion(
			List<SemanaPlanificacion> semanaPlanificacion) {
		for (SemanaPlanificacion unaSemanaPlanificacion: semanaPlanificacion) {
			for (DiaPlanificacion unDiaPlanificacion : unaSemanaPlanificacion.getDias()) {
				this.diaPlanificacionDAO.remove(unDiaPlanificacion.getId());
			}
			this.semanaPlanificacionDAO.remove(unaSemanaPlanificacion.getId());
		}
		
	}

	private void savePlanificacion(Planificacion planificacion) {
		saveScores(planificacion.getScores());
		
		saveSemanaPlanificacion(planificacion.getCuatrimestre1());
		saveSemanaPlanificacion(planificacion.getCuatrimestre2());
		saveSemanaPlanificacion(planificacion.getCuatrimestre3());
		saveSemanaPlanificacion(planificacion.getCuatrimestre4());
		saveSemanaPlanificacion(planificacion.getCuatrimestre5());
		
		this.planificacionDAO.saveOrUpdate(planificacion);
	}

	private void saveScores(List<Score> scores) {
		for (Score unScore: scores) {
			scoreDAO.saveOrUpdate(unScore);
		}
		
	}

	private void saveSemanaPlanificacion(List<SemanaPlanificacion> semanaPlanificacion) {
		for (SemanaPlanificacion unaSemanaPlanificacion: semanaPlanificacion) {
			for (DiaPlanificacion unDiaPlanificacion : unaSemanaPlanificacion.getDias()) {
				this.diaPlanificacionDAO.saveOrUpdate(unDiaPlanificacion);
			}
			this.semanaPlanificacionDAO.saveOrUpdate(unaSemanaPlanificacion);
		}
	}

	private Planificacion processParidadPar(List<PosiblesCursantesMateriaDTO> materias) throws NivelCompletoException {

		List<PosiblesCursantesMateriaDTO> segundoCuatrimestre = this.filtrarCuatrimestre(1, 2, materias);
		List<PosiblesCursantesMateriaDTO> cuartoCuatrimestre = this.filtrarCuatrimestre(2, 2, materias);
		List<PosiblesCursantesMateriaDTO> sextoCuatrimestre = this.filtrarCuatrimestre(3, 2, materias);
		List<PosiblesCursantesMateriaDTO> octavoCuatrimestre = this.filtrarCuatrimestre(4, 2, materias);
		List<PosiblesCursantesMateriaDTO> decimoCuatrimestre = this.filtrarCuatrimestre(5, 2, materias);

		List<SemanaPlanificacion> semanaSegundoCuatrimestre = procesarSemana(segundoCuatrimestre);
		List<SemanaPlanificacion> semanaCuartoCuatrimestre = procesarSemana(cuartoCuatrimestre);
		List<SemanaPlanificacion> semanaSextoCuatrimestre = procesarSemana(sextoCuatrimestre);
		List<SemanaPlanificacion> semanaOctavoCuatrimestre = procesarSemana(octavoCuatrimestre);
		List<SemanaPlanificacion> semanaDecimoCuatrimestre = procesarSemana(decimoCuatrimestre);

		Planificacion planificacion = new Planificacion();
		planificacion.setParidad(Paridad.PAR.toString());
		planificacion.setCuatrimestre1(semanaSegundoCuatrimestre);
		planificacion.setCuatrimestre2(semanaCuartoCuatrimestre);
		planificacion.setCuatrimestre3(semanaSextoCuatrimestre);
		planificacion.setCuatrimestre4(semanaOctavoCuatrimestre);
		planificacion.setCuatrimestre5(semanaDecimoCuatrimestre);
		
		ScoreStrategy strategy = new AlumnoCursadasStrategy();
		List<CursadaAlumnoDTO> cursadasAlumnos = strategy.processScore(planificacion, Paridad.PAR);
		
		this.calcularScoreTipoCuatrimestre(planificacion, strategy, cursadasAlumnos);
		
		planificacion.setScore(this.calcularScoreUnificado(planificacion.getScores()));
		
		this.calcularScoreCuatrimestres(planificacion, strategy);
				
		return planificacion;
	}
	
	private Planificacion processParidadImpar(List<PosiblesCursantesMateriaDTO> materias) throws NivelCompletoException {

		List<PosiblesCursantesMateriaDTO> primerCuatrimestre = this.filtrarCuatrimestre(1, 1, materias);
		List<PosiblesCursantesMateriaDTO> tercerCuatrimestre = this.filtrarCuatrimestre(2, 1, materias);
		List<PosiblesCursantesMateriaDTO> quintoCuatrimestre = this.filtrarCuatrimestre(3, 1, materias);
		List<PosiblesCursantesMateriaDTO> septimoCuatrimestre = this.filtrarCuatrimestre(4, 1, materias);
		List<PosiblesCursantesMateriaDTO> novenoCuatrimestre = this.filtrarCuatrimestre(5, 1, materias);

		List<SemanaPlanificacion> semanaPrimerCuatrimestre = procesarSemana(primerCuatrimestre);
		List<SemanaPlanificacion> semanaTercerCuatrimestre = procesarSemana(tercerCuatrimestre);
		List<SemanaPlanificacion> semanaQuintoCuatrimestre = procesarSemana(quintoCuatrimestre);
		List<SemanaPlanificacion> semanaSeptimoCuatrimestre = procesarSemana(septimoCuatrimestre);
		List<SemanaPlanificacion> semanaNovenoCuatrimestre = procesarSemana(novenoCuatrimestre);

		Planificacion planificacion = new Planificacion();
		planificacion.setParidad(Paridad.IMPAR.toString());
		planificacion.setCuatrimestre1(semanaPrimerCuatrimestre);
		planificacion.setCuatrimestre2(semanaTercerCuatrimestre);
		planificacion.setCuatrimestre3(semanaQuintoCuatrimestre);
		planificacion.setCuatrimestre4(semanaSeptimoCuatrimestre);
		planificacion.setCuatrimestre5(semanaNovenoCuatrimestre);
		
		ScoreStrategy strategy = new AlumnoCursadasStrategy();
		List<CursadaAlumnoDTO> cursadasAlumnos = strategy.processScore(planificacion, Paridad.IMPAR);
		
		this.calcularScoreTipoCuatrimestre(planificacion, strategy, cursadasAlumnos);
		
		planificacion.setScore(this.calcularScoreUnificado(planificacion.getScores()));
		
		this.calcularScoreCuatrimestres(planificacion, strategy);
				
		return planificacion;
	}

	private void calcularScoreCuatrimestres(Planificacion planificacion, ScoreStrategy strategy) {
		
		List<Alumno> all = this.alumnoDAO.findAll();
		planificacion.setScoreCuatrimestre1(this.calcularScorePorCuatrimestre(planificacion, strategy, planificacion.getCuatrimestre1(), all));
		planificacion.setScoreCuatrimestre2(this.calcularScorePorCuatrimestre(planificacion, strategy, planificacion.getCuatrimestre2(), all));
		planificacion.setScoreCuatrimestre3(this.calcularScorePorCuatrimestre(planificacion, strategy, planificacion.getCuatrimestre3(), all));
		planificacion.setScoreCuatrimestre4(this.calcularScorePorCuatrimestre(planificacion, strategy, planificacion.getCuatrimestre4(), all));
		planificacion.setScoreCuatrimestre5(this.calcularScorePorCuatrimestre(planificacion, strategy, planificacion.getCuatrimestre5(), all));
	}

	private Integer calcularScorePorCuatrimestre(Planificacion planificacion, ScoreStrategy strategy, 
			List<SemanaPlanificacion> nivelesSemana, List<Alumno> all) {
		
		List<Score> scores = new ArrayList<Score>();
		List<CursadaAlumnoDTO> cursadas = new ArrayList<CursadaAlumnoDTO>();
		
		strategy.procesarCuatrimestre(nivelesSemana, cursadas);
				
		Score scoreEnCero = this.calcularScoreDeCeroMaterias(cursadas, all);
		scores.add(scoreEnCero);
		
		int i = 1;
		while (i <= 7) {
			Score score = strategy.calcularScore(i, cursadas);
			scores.add(score);
			i++;
		}
		
		return calcularScoreUnificado(scores);
	}

	private void calcularScoreTipoCuatrimestre(Planificacion planificacion, ScoreStrategy strategy, List<CursadaAlumnoDTO> cursadasAlumnos) {
		planificacion.getScores().clear();
		
		List<Alumno> all = this.alumnoDAO.findAll();
		Integer resto = all.size();
		
		Score scoreEnCero = this.calcularScoreDeCeroMaterias(cursadasAlumnos, all);
		resto -= scoreEnCero.getAlumnos().size();
		planificacion.getScores().add(scoreEnCero);
		
		int i = 1;
		while (resto > 0) {
			Score score = strategy.calcularScore(i, cursadasAlumnos);
			planificacion.getScores().add(score);
			i++;
			resto -= score.getAlumnos().size();
		}
	}

	private Integer calcularScoreUnificado(List<Score> scores) {
		Integer score = 0;
		
		for (Score unScore: scores) {
			score += (unScore.getCantidadMaterias() * unScore.getAlumnos().size());
		}
		
		return score;
	}

	private Score calcularScoreDeCeroMaterias(
			List<CursadaAlumnoDTO> cursadasAlumnos, List<Alumno> findAll) {
		
		List<Alumno> alumnosCursadas = new ArrayList<Alumno>();
		
		for (CursadaAlumnoDTO unaCursada: cursadasAlumnos) {
			alumnosCursadas.add(unaCursada.getAlumno());
		}
		
		Score score = new Score();
		score.setCantidadMaterias(0);
		score.getAlumnos().addAll(this.differenceList(alumnosCursadas, findAll));
		
		return score;
	}
	
	private List<Alumno> differenceList(List<Alumno> lista1, List<Alumno> lista2) {

		Set<Alumno> result = new HashSet<Alumno>();
		
		for (Alumno unAlumno: lista1) {
			if (!lista2.contains(unAlumno)) {
				result.add(unAlumno);
			}
		}
		
		for (Alumno unAlumno: lista2) {
			if (!lista1.contains(unAlumno)) {
				result.add(unAlumno);
			}
		}
		
		return new ArrayList<Alumno>(result);
	}

	private List<PosiblesCursantesMateriaDTO> filtrarCuatrimestre(Integer anio,	Integer cuatrimestre, 
			List<PosiblesCursantesMateriaDTO> materias) {

		List<PosiblesCursantesMateriaDTO> result = new ArrayList<PosiblesCursantesMateriaDTO>();

		for (PosiblesCursantesMateriaDTO dto : materias) {
			if (dto.getMateria().getAnio().equals(anio)	&& dto.getMateria().getCuatrimestre().equals(cuatrimestre)) {
				result.add(dto);
			}
		}

		return result;
	}

	private List<SemanaPlanificacion> procesarSemana (List<PosiblesCursantesMateriaDTO> materiasCuatrimestre)
			throws NivelCompletoException {

		List<SemanaPlanificacion> filasSemanas = new ArrayList<SemanaPlanificacion>();

		List<ColisionMateriaDTO> colisiones = this.procesarColisiones(materiasCuatrimestre);

		Collections.sort(colisiones, new ColisionMateriaComparator());

		SemanaPlanificacion semanaNivel1 = new SemanaPlanificacion();
		SemanaPlanificacion SemanaNivel2 = new SemanaPlanificacion();

		List<Integer> idsMateriasAgregadas = new ArrayList<Integer>();

		int i;
		for (i = 0; i < colisiones.size(); i++) {
			ColisionMateriaDTO colision = colisiones.get(i);

			colocarEnDiaLibre(semanaNivel1, SemanaNivel2, colision.getMateria1(), Boolean.FALSE, idsMateriasAgregadas);

			if (colision.getMateria1().getMateria().getHoras() > 60) {
				colocarEnDiaLibre(semanaNivel1, SemanaNivel2, colision.getMateria1(), Boolean.TRUE, idsMateriasAgregadas);
			}

			colocarEnDiaLibre(semanaNivel1, SemanaNivel2, colision.getMateria2(), Boolean.FALSE, idsMateriasAgregadas);

			if (colision.getMateria2().getMateria().getHoras() > 60) {
				colocarEnDiaLibre(semanaNivel1, SemanaNivel2, colision.getMateria2(), Boolean.TRUE, idsMateriasAgregadas);
			}
		}

		filasSemanas.add(semanaNivel1);
		filasSemanas.add(SemanaNivel2);

		return filasSemanas;
	}

	private void colocarEnDiaLibre(SemanaPlanificacion semanaNivel1, SemanaPlanificacion semanaNivel2, PosiblesCursantesMateriaDTO materia,
			Boolean dosDias, List<Integer> idsAgregados) throws NivelCompletoException {

		if (!idsAgregados.contains(materia.getMateria().getId())
				|| ((materia.getMateria().getHoras() > 60) && (!estaCargadaDosVeces(idsAgregados, materia)))) {
			try {
				ManagerDePlanificacionSemanal.colocarEnDiaLibre(semanaNivel1, materia);
			} catch (NivelCompletoException nce) {
				ManagerDePlanificacionSemanal.colocarEnDiaLibre(semanaNivel2, materia);
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
			PosiblesCursantesMateriaDTO posiblesCursantes1 = materiasCuatrimestre.get(i);

			for (j = i + 1; j < materiasCuatrimestre.size(); j++) {
				PosiblesCursantesMateriaDTO posiblesCursantes2 = materiasCuatrimestre.get(j);

				ColisionMateriaDTO colisionMateriaDTO = new ColisionMateriaDTO(posiblesCursantes1, posiblesCursantes2);

				colisionMateriaDTO.getColisiones().addAll(this.interseccion(posiblesCursantes1
								.getAlumnosPosiblesCursantes(), posiblesCursantes2.getAlumnosPosiblesCursantes()));

				result.add(colisionMateriaDTO);
			}
		}

		return result;
	}

	private Collection<? extends Alumno> interseccion(List<Alumno> alumnosPosiblesCursantes,
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