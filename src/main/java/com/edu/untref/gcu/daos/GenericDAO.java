package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import com.edu.untref.gcu.domain.PersistibleObject;

public interface GenericDAO<E extends PersistibleObject, PK extends Serializable> {
	
	void save(E object);

    void update(E object);

    void saveOrUpdate(E object);

    void remove(PK id);

    E findById(PK id);
    
    List<E> findAll(); 
	
}