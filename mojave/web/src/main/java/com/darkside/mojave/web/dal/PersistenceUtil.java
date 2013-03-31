package com.darkside.mojave.web.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
	
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
