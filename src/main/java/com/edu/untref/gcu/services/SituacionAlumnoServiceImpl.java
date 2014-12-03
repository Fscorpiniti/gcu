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
			Integer idMateria = materiaService.findIdMateriaByCodigo(((Double) rowNombreMaterias.getCell(i).getNumericCellValue()).intValue());
			PlanMateria planMateria = planMateriaService.findPlanMateriaByIdMateria(idMateria);
			SituacionAlumno situacionAlumno = situacionAlumnoDAO.findSituacionByIdPlanMateriaAndIdAlumno(planMateria.getId(), alumno);
			
			if(situacionAlumno != null){
				String estadoMateria;
				try{
					estadoMateria = row.getCell(i).getStringCellValue();
				}catch(IllegalStateException ise){
					estadoMateria = String.valueOf(row.getCell(i).getNumericCellValue());
				}catch(NullPointerException npe){
					estadoMateria = null;
				}
				
				if(estadoMateria != null){
					this.actulizarSituacionAlumno(situacionAlumno, estadoMateria);
				}
				
			}else{
				this.crearSituacionAlumno(row, alumno, planMateria, idMateria);
			}
		}
	}

	private void crearSituacionAlumno(Row row, Alumno alumno,
			PlanMateria planMateria, Integer idMateria) {
		SituacionAlumno situacionNueva = new SituacionAlumno();
		situacionNueva.setAlumno(alumno);
		situacionNueva.setFecha(new Date());
		situacionNueva.setMateria(planMateria);
		situacionAlumnoDAO.save(situacionNueva);
	}

	private void actulizarSituacionAlumno(SituacionAlumno situacionAlumno,
			String estadoMateria) {
		
		if(estadoMateria.equals("C")){
			situacionAlumno.setEstado(EstadoMateria.CURSANDO);
		}else if(esNumeroYAprobado(estadoMateria) || estadoMateria.equals("A.P.E")){
			situacionAlumno.setEstado(EstadoMateria.APROBADO);
		}else if(estadoMateria.equals("Ab")){
			situacionAlumno.setEstado(EstadoMateria.ABANDONADO);
		}else if(estadoMateria.equals("A")){
			situacionAlumno.setEstado(EstadoMateria.REGULAR);
		}
		
		situacionAlumno.setFecha(new Date());
		situacionAlumnoDAO.saveOrUpdate(situacionAlumno);
	}

	@Override
	public void crearSituacionMaterias(Row row, Alumno alumno, Row rowNombreMaterias) {
		for(int i = 5; i<row.getLastCellNum(); i++){
			Integer idMateria = materiaService.findIdMateriaByCodigo(((Double) rowNombreMaterias.getCell(i).getNumericCellValue()).intValue());
			PlanMateria planMateria = planMateriaService.findPlanMateriaByIdMateria(idMateria);
			this.crearSituacionAlumno(rowNombreMaterias, alumno, planMateria, idMateria);
		}		
	}
	
	public static boolean esNumeroYAprobado(String str){  

		double d;
		try{  
			d = Double.parseDouble(str);
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
