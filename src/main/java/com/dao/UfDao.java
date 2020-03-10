package com.dao;

import javax.persistence.EntityManager;

import com.model.Uf;

public class UfDao {
	private EntityManager em;

	public UfDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Uf uf) {
		em.persist(uf);
	}

}
