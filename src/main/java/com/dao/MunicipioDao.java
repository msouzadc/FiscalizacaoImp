package com.dao;

import javax.persistence.EntityManager;

import com.model.Municipio;

public class MunicipioDao {
	private EntityManager em;

	public MunicipioDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Municipio municipio) {
		em.persist(municipio);
	}


}
