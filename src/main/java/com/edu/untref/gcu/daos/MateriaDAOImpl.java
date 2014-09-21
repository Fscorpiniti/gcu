package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Materia;

@Repository("materiaDAO")
public class MateriaDAOImpl extends GenericDAOImpl<Materia, Serializable> implements MateriaDAO {

	@Override
	protected Class<Materia> getEntityClass() {
		return Materia.class;
	}

}
