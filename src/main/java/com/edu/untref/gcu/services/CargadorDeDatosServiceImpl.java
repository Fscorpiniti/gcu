package com.edu.untref.gcu.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.edu.untref.gcu.domain.Alumno;

@Transactional
@Service("cargadorDeDatosService")
public class CargadorDeDatosServiceImpl implements CargadorDeDatosService{
	
	@Resource(name = "alumnoService")
	private AlumnoService alumnoService;
	
	@Resource(name = "planEstudioService")
	private PlanEstudioService planEstudioService;
	
	@Resource(name = "situacionAlumnoService")
	private SituacionAlumnoService situacionAlumnoService;
	
	@Override
	public void evaluarXlsx(File inputFile) throws FileNotFoundException {
		try {
            XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
            XSSFSheet sheet = wBook.getSheetAt(0);
            
            Row row;
            
            Iterator<Row> rowIterator = sheet.iterator();
            
            Row rowCodigos = rowIterator.next();
            
            while (rowIterator.hasNext()) {

            	row = rowIterator.next();
            	Integer anioPlanEstudio = ((Double)row.getCell(3).getNumericCellValue()).intValue();

            	if(anioPlanEstudio.equals(Integer.valueOf(2007))){
            		this.agregarAlumnoAlScriptAlumno(row, rowCodigos);
            	}
            }
            
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
	}

	private void agregarAlumnoAlScriptAlumno(Row row, Row rowCodigos) throws IOException {
		
        Integer legajo = ((Double)row.getCell(0).getNumericCellValue()).intValue();
        String apellido = row.getCell(1).getStringCellValue();
        String nombre = row.getCell(2).getStringCellValue();
        Integer anioIngreso = ((Double)row.getCell(4).getNumericCellValue()).intValue();
		
        Alumno alumnoBase = alumnoService.findByLejago(legajo);
        
        if(alumnoBase != null){
        	this.situacionAlumnoService.actualizarSituacionMaterias(row, alumnoBase, rowCodigos);
        }else{
        	Alumno alumno = new Alumno();
        	alumno.setLegajo(legajo);
        	alumno.setApellido(apellido);
        	alumno.setNombre(nombre);
        	alumno.setAnioIngreso(anioIngreso);
        	alumno.setPlanEstudio(this.planEstudioService.getPlanEstudioById("1"));
        	
        	alumnoService.save(alumno);
        	
        	this.situacionAlumnoService.crearSituacionMaterias(row, alumno, rowCodigos);
        }
	}

	
}
