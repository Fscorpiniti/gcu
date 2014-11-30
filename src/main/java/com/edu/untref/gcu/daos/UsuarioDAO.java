package com.edu.untref.gcu.daos;

import java.io.Serializable;

import com.edu.untref.gcu.domain.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Serializable> {

	Usuario findByUsername(String username);
	
}
