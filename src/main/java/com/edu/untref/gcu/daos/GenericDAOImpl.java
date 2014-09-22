package com.edu.untref.gcu.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.PersistibleObject;

@Repository("genericDAO")
public abstract class GenericDAOImpl<E extends PersistibleObject, PK extends Serializable> 
				implements GenericDAO<E, PK> {
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "gcuPersistenceUnit")
	private EntityManager entityManager;

	protected abstract Class<E> getEntityClass();

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void save(E object) {
		this.entityManager.persist(object);
	}
	
	@Override
	public E findById(PK id) {
		return (E) this.entityManager.find(getEntityClass(), id);
	}
	
	@Override
	public void update(E object) {
		this.entityManager.merge(object);
	}
	
	@Override
	public void saveOrUpdate(E object) {
		if (object.getId() == 0) {
			save(object);
		} else {
			update(object);
		}
	}
	
	@Override
	public void remove(PK id) {
		E entity = this.entityManager.getReference(getEntityClass(), id);

		this.entityManager.remove(entity);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		StringBuilder queryBuilder = new StringBuilder("from ");
		queryBuilder.append(getEntityClass().getCanonicalName());
		queryBuilder.append(" this");

		return this.entityManager.createQuery(queryBuilder.toString()).getResultList();
	}
	
}