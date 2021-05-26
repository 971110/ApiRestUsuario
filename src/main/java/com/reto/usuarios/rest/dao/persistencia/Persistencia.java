package com.reto.usuarios.rest.dao.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Persistencia {

	private static EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	
	private void temp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("persistenceH2");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
}
