package com.edu.untref.gcu.services;

import org.apache.poi.ss.usermodel.Row;

import com.edu.untref.gcu.domain.Alumno;

public interface SituacionAlumnoService {

	void actualizarSituacionMaterias(Row row, Alumno alumno, Row rowNombreMaterias);

	void crearSituacionMaterias(Row row, Alumno alumno, Row rowNombreMaterias);

}
