package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.DiaPlanificacion;

@Repository("diaPlanificacionDAO")
public class DiaPlanificacionDAOImpl extends GenericDAOImpl<DiaPlanificacion, Serializable> implements DiaPlanificacionDAO {

	@Override
	protected Class<DiaPlanificacion> getEntityClass() {
		return DiaPlanificacion.class;
	}

}
