package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.model.Empresa;

public class EmpresaDao {
	
	private EntityManager em;

	public EmpresaDao (EntityManager em) {
		this.em = em;
	}

	public void inserir(Empresa empresa) {
		em.persist(empresa);
	}
	
	public void atualizar(Empresa empresa) {
		em.merge(empresa);
	}
	
	public Empresa busca(String cnpj) {
		try {
			TypedQuery<Empresa> query = em.createQuery(
					"SELECT e FROM Empresa e WHERE e.cnpj = :cnpj  ", Empresa.class);
			query.setParameter("cnpj", cnpj);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}

	}



}
