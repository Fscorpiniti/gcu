package com.edu.untref.gcu.services;

import com.edu.untref.gcu.domain.Materia;


public interface MateriaService {
	
	Integer findIdMateriaByCodigo(Integer codigoMateria);
	
	Materia findMateriaByCodigo(Integer codigoMateria);

}
