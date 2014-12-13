package com.edu.untref.gcu.daos;

import java.io.Serializable;

import com.edu.untref.gcu.domain.Materia;

public interface MateriaDAO extends GenericDAO<Materia, Serializable> {

	Integer findIdMateriaByCodigo(Integer codigoMateria);

	Materia findMateriaByCodigo(Integer codigoMateria);

}
