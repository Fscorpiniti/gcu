package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.SemanaPlanificacion;

@Repository("semanaPlanificacionDAO")
public class SemanaPlanificacionDAOImpl extends GenericDAOImpl<SemanaPlanificacion, Serializable> implements SemanaPlanificacionDAO {

	@Override
	protected Class<SemanaPlanificacion> getEntityClass() {
		return SemanaPlanificacion.class;
	}

}
