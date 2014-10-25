package com.edu.untref.gcu.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.AlumnoDAO;
import com.edu.untref.gcu.daos.PlanEstudioDAO;
import com.edu.untref.gcu.daos.SituacionAlumnoDAO;
import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.PlanEstudio;
import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;

@Transactional
@Service("planEstudioService")
public class PlanEstudioServiceImpl implements PlanEstudioService {

	@Resource(name = "planEstudioDAO")
	private PlanEstudioDAO planEstudioDAO;

	@Resource(name = "planMateriaService")
	private PlanMateriaService planMateriaService;

	@Resource(name = "situacionAlumnoDAO")
	private SituacionAlumnoDAO situacionAlumnoDAO;

	@Resource(name = "alumnoDAO")
	private AlumnoDAO alumnoDAO;

	public List<PlanEstudio> getAllPlanesByCarrera(Integer idCarrera) {
		return this.planEstudioDAO.getAllPlanesByIdCarrera(idCarrera);
	}

	@Override
	public List<PlanMateria> getAllPlanMateriasByIdPlanEstudio(String id) {
		return planMateriaService.getAllPlanMateriasByIdPlanEstudio(id);
	}

	@Override
	public List<PosiblesCursantesMateriaDTO> getAllPosiblesCursantesMaterias(
			String id) {

		List<PosiblesCursantesMateriaDTO> result = new ArrayList<PosiblesCursantesMateriaDTO>();

		List<PlanMateria> planMaterias = getAllPlanMateriasByIdPlanEstudio(id);

		Iterator<PlanMateria> iteradorPlanMaterias = planMaterias.iterator();

		while (iteradorPlanMaterias.hasNext()) {
			PlanMateria materia = iteradorPlanMaterias.next();

			List<Alumno> sinProbabilidadesCursar = this.situacionAlumnoDAO
					.findNoPosiblesCursantes(materia);

			if (materia.getCorrelativa() == null) {
				procesarMateriaSinCorrelativa(result, materia, sinProbabilidadesCursar);
			} else {
				procesarMateriaConCorrelativa(result, materia, sinProbabilidadesCursar);
			}

		}

		return result;
	}

	private void procesarMateriaConCorrelativa(List<PosiblesCursantesMateriaDTO> result,
			PlanMateria materia, List<Alumno> sinProbabilidadesCursar) {
		
		List<Alumno> alumnosConCorrelativa = this.situacionAlumnoDAO
				.findAlumnosCorrelacionHabilitada(materia
						.getCorrelativa());

		List<Alumno> difference = this.differenceList(
				sinProbabilidadesCursar, alumnosConCorrelativa);
		
		result.add(new PosiblesCursantesMateriaDTO(materia, difference));
	}

	private void procesarMateriaSinCorrelativa(List<PosiblesCursantesMateriaDTO> result,
			PlanMateria materia, List<Alumno> sinProbabilidadesCursar) {
		
		List<Alumno> posiblesCursantes = this.alumnoDAO
				.findDifferenceList(sinProbabilidadesCursar);

		result.add(new PosiblesCursantesMateriaDTO(materia, posiblesCursantes));
	}

	private List<Alumno> differenceList(List<Alumno> noPosiblesCursantes,
			List<Alumno> alumnosConCorrelativa) {

		List<Alumno> result = new ArrayList<Alumno>();
		
		for (Alumno unAlumnoConCorrelativa: alumnosConCorrelativa) {
			if (!noPosiblesCursantes.contains(unAlumnoConCorrelativa)) {
				result.add(unAlumnoConCorrelativa);
			}
		}
		
		return result;
	}

}