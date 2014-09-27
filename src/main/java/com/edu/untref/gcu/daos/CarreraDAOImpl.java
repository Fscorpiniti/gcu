package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Carrera;

@Repository("carreraDAO")
public class CarreraDAOImpl extends GenericDAOImpl<Carrera, Serializable> implements CarreraDAO {

	@Override
	protected Class<Carrera> getEntityClass() {
		return Carrera.class;
	}

}
