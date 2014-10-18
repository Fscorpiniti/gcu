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

			List<Alumno> noPosiblesCursantes = this.situacionAlumnoDAO
					.findNoPosiblesCursantes(materia);

			if (materia.getCorrelativa() == null) {
				List<Alumno> posiblesCursantes = this.alumnoDAO
						.findDifferenceList(noPosiblesCursantes);

				result.add(new PosiblesCursantesMateriaDTO(materia,
						posiblesCursantes.size()));
			} else {
				List<Alumno> alumnosConCorrelativa = this.situacionAlumnoDAO
						.findAlumnosCorrelacionHabilitada(materia
								.getCorrelativa());

				List<Alumno> difference = this.differenceList(
						noPosiblesCursantes, alumnosConCorrelativa);
				
				result.add(new PosiblesCursantesMateriaDTO(materia,
						difference.size()));
			}

		}

		return result;
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