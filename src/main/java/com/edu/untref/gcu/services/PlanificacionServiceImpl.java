package com.edu.untref.gcu.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.untref.gcu.domain.Paridad;
import com.edu.untref.gcu.dtos.NivelPlanificacionDTO;
import com.edu.untref.gcu.dtos.PlanificacionCuatrimestreDTO;
import com.edu.untref.gcu.dtos.PosiblesCursantesMateriaDTO;

@Service("planificacionService")
public class PlanificacionServiceImpl implements PlanificacionService {

	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;

	@Override
	public PlanificacionCuatrimestreDTO planificar(String id, String paridad) {

		Paridad paridadSeleccionada = Paridad.valueOf(paridad);
		
		List<PosiblesCursantesMateriaDTO> materias = planEstudioService.getAllMateriasByCuatrimestre(id, paridadSeleccionada);
			
		PlanificacionCuatrimestreDTO cuatrimestreDTO = new PlanificacionCuatrimestreDTO();
		
		if (paridadSeleccionada.equals(Paridad.PAR)) {
			cuatrimestreDTO = processParidadPar(materias, cuatrimestreDTO);
		} else {
			cuatrimestreDTO = processParidadImpar(materias, cuatrimestreDTO);
		}
	
		return cuatrimestreDTO;
	}

	private PlanificacionCuatrimestreDTO processParidadPar(List<PosiblesCursantesMateriaDTO> materias, PlanificacionCuatrimestreDTO cuatrimestreDTO) {

		List<PosiblesCursantesMateriaDTO> segundoCuatrimestre = this.filtrarCuatrimestre(1, 2, materias);
		List<PosiblesCursantesMateriaDTO> cuartoCuatrimestre = this.filtrarCuatrimestre(2, 2, materias);
		List<PosiblesCursantesMateriaDTO> sextoCuatrimestre = this.filtrarCuatrimestre(3, 2, materias);
		List<PosiblesCursantesMateriaDTO> octavoCuatrimestre = this.filtrarCuatrimestre(4, 2, materias);
		List<PosiblesCursantesMateriaDTO> decimoCuatrimestre = this.filtrarCuatrimestre(5, 2, materias);
		
		NivelPlanificacionDTO nivelSegundoCuatriDTO = processNivel(segundoCuatrimestre);
		NivelPlanificacionDTO nivelCuartoCuatriDTO = processNivel(cuartoCuatrimestre);
		NivelPlanificacionDTO nivelSextoCuatriDTO = processNivel(sextoCuatrimestre);
		NivelPlanificacionDTO nivelOctavoCuatriDTO = processNivel(octavoCuatrimestre);
		NivelPlanificacionDTO nivelDecimoCuatriDTO = processNivel(decimoCuatrimestre);
		
		cuatrimestreDTO.getNivelesSegundoCuatri().add(nivelSegundoCuatriDTO);
		cuatrimestreDTO.getNivelesCuartoCuatri().add(nivelCuartoCuatriDTO);
		cuatrimestreDTO.getNivelesSextoCuatri().add(nivelSextoCuatriDTO);
		cuatrimestreDTO.getNivelesOctavoCuatri().add(nivelOctavoCuatriDTO);
		cuatrimestreDTO.getNivelesDecimoCuatri().add(nivelDecimoCuatriDTO);
				
		return cuatrimestreDTO;
	}

	private PlanificacionCuatrimestreDTO processParidadImpar(List<PosiblesCursantesMateriaDTO> materias, PlanificacionCuatrimestreDTO cuatrimestreDTO) {
	
		List<PosiblesCursantesMateriaDTO> primerCuatrimestre = this.filtrarCuatrimestre(1, 1, materias);
		List<PosiblesCursantesMateriaDTO> tercerCuatrimestre = this.filtrarCuatrimestre(2, 1, materias);
		List<PosiblesCursantesMateriaDTO> quintoCuatrimestre = this.filtrarCuatrimestre(3, 1, materias);
		List<PosiblesCursantesMateriaDTO> septimoCuatrimestre = this.filtrarCuatrimestre(4, 1, materias);
		List<PosiblesCursantesMateriaDTO> novenoCuatrimestre = this.filtrarCuatrimestre(5, 1, materias);
		
		NivelPlanificacionDTO nivelPrimeroCuatriDTO = processNivel(primerCuatrimestre);
		NivelPlanificacionDTO nivelTerceroCuatriDTO = processNivel(tercerCuatrimestre);
		NivelPlanificacionDTO nivelQuintoCuatriDTO = processNivel(quintoCuatrimestre);
		NivelPlanificacionDTO nivelSeptimoCuatriDTO = processNivel(septimoCuatrimestre);
		NivelPlanificacionDTO nivelNovenoCuatriDTO = processNivel(novenoCuatrimestre);
		
		cuatrimestreDTO.getNivelesPrimerCuatri().add(nivelPrimeroCuatriDTO);
		cuatrimestreDTO.getNivelesTercerCuatri().add(nivelTerceroCuatriDTO);
		cuatrimestreDTO.getNivelesQuintoCuatri().add(nivelQuintoCuatriDTO);
		cuatrimestreDTO.getNivelesSeptimoCuatri().add(nivelSeptimoCuatriDTO);
		cuatrimestreDTO.getNivelesNovenoCuatri().add(nivelNovenoCuatriDTO);
				
		return cuatrimestreDTO;
	}

	private List<PosiblesCursantesMateriaDTO> filtrarCuatrimestre(Integer anio, Integer cuatrimestre,
			List<PosiblesCursantesMateriaDTO> materias) {
		
		List<PosiblesCursantesMateriaDTO> result = new ArrayList<PosiblesCursantesMateriaDTO>();
		
		for (PosiblesCursantesMateriaDTO dto : materias) {
			if (dto.getMateria().getAnio().equals(anio) && dto.getMateria().getCuatrimestre().equals(cuatrimestre)) {
				result.add(dto);
			}
		}
		
		return result;
	}
	
	private NivelPlanificacionDTO processNivel(List<PosiblesCursantesMateriaDTO> cuatrimestre) {
		NivelPlanificacionDTO nivelPlanificacionDTO = new NivelPlanificacionDTO();
		nivelPlanificacionDTO.setLunes(cuatrimestre.remove(0));
		nivelPlanificacionDTO.setMartes(cuatrimestre.remove(0));
		nivelPlanificacionDTO.setMiercoles(cuatrimestre.remove(0));
		nivelPlanificacionDTO.setJueves(cuatrimestre.remove(0));
		nivelPlanificacionDTO.setViernes(cuatrimestre.remove(0));
		
		if (cuatrimestre.size() > 0) {
			nivelPlanificacionDTO.setSabado(cuatrimestre.remove(0));
		}
		
		return nivelPlanificacionDTO;
	}

}