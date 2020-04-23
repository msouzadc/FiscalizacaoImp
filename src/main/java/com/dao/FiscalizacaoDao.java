package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.model.Empresa;
import com.model.Fiscalizacao;

public class FiscalizacaoDao {
	private EntityManager em;
	
	public FiscalizacaoDao (EntityManager em) {
		this.em=em;
	}
	
	public void inserir (Fiscalizacao fiscalizacao) {
		em.persist(fiscalizacao);
	}

	 public Fiscalizacao busca(Empresa empresa, String data) {
		 try {
				TypedQuery<Fiscalizacao> query = em.createQuery(
						"SELECT f FROM Fiscalizacao f WHERE f.empresa = :empresa and f.data = :data ", Fiscalizacao.class);
				query.setParameter("empresa", empresa);
				query.setParameter("data", data);
				return  query.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
	 }

}
