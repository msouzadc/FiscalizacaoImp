package com.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("postgresql");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
