package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.model.Bairro;
import com.model.Municipio;

public class BairroDao {
	
	private EntityManager em;

	public BairroDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Bairro bairro) {
		em.persist(bairro);
	}

	public Bairro busca(Municipio municipio, String Bairro) {
		try {
			TypedQuery<Bairro> query = em.createQuery("SELECT u FROM Bairro u WHERE u.nome = :Bairro and u.municipio = :municipio", Bairro.class);
			query.setParameter("Bairro", Bairro);
			query.setParameter("municipio", municipio);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}	
	}
	

}
