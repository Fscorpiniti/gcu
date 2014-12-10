package com.edu.untref.gcu.services;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.edu.untref.gcu.daos.SituacionAlumnoDAO;
import com.edu.untref.gcu.domain.Alumno;
import com.edu.untref.gcu.domain.EstadoMateria;
import com.edu.untref.gcu.domain.PlanMateria;
import com.edu.untref.gcu.domain.SituacionAlumno;

@Service("situacionAlumnoService")
public class SituacionAlumnoServiceImpl implements SituacionAlumnoService{

	@Resource(name = "situacionAlumnoDAO")
	private SituacionAlumnoDAO situacionAlumnoDAO;
	
	@Resource (name = "materiaService")
	private MateriaService materiaService;
	
	@Resource (name = "planMateriaService")
	private PlanMateriaService planMateriaService;
	
	
	@Override
	public void actualizarSituacionMaterias(Row row, Alumno alumno, Row rowNombreMaterias) {
		for(int i = 5; i<row.getLastCellNum(); i++){
			
			Integer idMateria = null;
			
			try{
				idMateria = materiaService.findIdMateriaByCodigo(((Double) rowNombreMaterias.getCell(i).getNumericCellValue()).intValue());
			}catch(NullPointerException npe){
				idMateria = null;
			}
			
			
			if(idMateria != null){
				
				PlanMateria planMateria = planMateriaService.findPlanMateriaByIdMateria(idMateria);
				
				if(planMateria != null){
					SituacionAlumno situacionAlumno = situacionAlumnoDAO.findSituacionByIdPlanMateriaAndIdAlumno(planMateria.getId(), alumno);
					
					if(situacionAlumno != null){
						Object estadoMateria = obtenerEstadoMateria(row, i);
						if(estadoMateria != null){
							this.actulizarSituacionAlumno(situacionAlumno, estadoMateria);
						}
						
					}else{
						Object estadoMateria = obtenerEstadoMateria(row, i);
						if(estadoMateria != null){
							this.crearSituacionAlumno(row, alumno, planMateria, idMateria, estadoMateria);
						}
					}
				}
			}
		}
	}

	private Object obtenerEstadoMateria(Row row, int i) {
		Object estadoMateria;
		try{
			estadoMateria = row.getCell(i).getStringCellValue();
		}catch(IllegalStateException ise){
			estadoMateria = String.valueOf(row.getCell(i).getNumericCellValue());
		}catch(NullPointerException npe){
			estadoMateria = null;
		}
		return estadoMateria;
	}

	private void crearSituacionAlumno(Row row, Alumno alumno,
			PlanMateria planMateria, Integer idMateria, Object estadoMateria) {
		SituacionAlumno situacionNueva = new SituacionAlumno();
		situacionNueva.setAlumno(alumno);
		situacionNueva.setFecha(new Date());
		situacionNueva.setMateria(planMateria);
		
		this.actulizarSituacionAlumno(situacionNueva, estadoMateria);
	}

	private void actulizarSituacionAlumno(SituacionAlumno situacionAlumno,
			Object estadoMateria) {
		
		if(estadoMateria.equals("C")){
			situacionAlumno.setEstado(EstadoMateria.CURSANDO);
		}else if(estadoMateria.equals("V")){
			situacionAlumno.setEstado(null);
		}else if(estadoMateria.equals("Ab")){
			situacionAlumno.setEstado(EstadoMateria.ABANDONADO);
		}else if(estadoMateria.equals("A")){
			situacionAlumno.setEstado(EstadoMateria.REGULAR);
		}else if(estadoMateria.equals(null)){
			situacionAlumno.setEstado(null);
		}else if(estadoMateria.equals("S/A")){
			situacionAlumno.setEstado(null);
		}else if(esNumeroYAprobado(estadoMateria) || estadoMateria.equals("A.P.E")){
			situacionAlumno.setEstado(EstadoMateria.APROBADO);
		}
		
		situacionAlumno.setFecha(new Date());
		situacionAlumnoDAO.saveOrUpdate(situacionAlumno);
	}

	@Override
	public void crearSituacionMaterias(Row row, Alumno alumno, Row rowNombreMaterias) {
		for(int i = 5; i<row.getLastCellNum(); i++){

			Integer idMateria = null;
			
			try{
				idMateria = materiaService.findIdMateriaByCodigo(((Double) rowNombreMaterias.getCell(i).getNumericCellValue()).intValue());
			}catch(NullPointerException npe){
				idMateria = null;
			}
			
			if(idMateria != null){
			
				PlanMateria planMateria = planMateriaService.findPlanMateriaByIdMateria(idMateria);
				
				if(planMateria != null){
					Object estadoMateria = obtenerEstadoMateria(row, i);
					if(estadoMateria != null){
						this.crearSituacionAlumno(rowNombreMaterias, alumno, planMateria, idMateria, estadoMateria);
					}
				}
			}
		}		
	}
	
	public static boolean esNumeroYAprobado(Object str){  

		double d;
		try{  
			d = Double.parseDouble((String) str);
		}catch(NumberFormatException nfe){  
		    return false;  
		}
		if(d > 3.0){
			return true;
		}else{
			return false;
		}
	}

}
