package br.edu.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.modelo.Uf;

public class UfDao {
	private EntityManager em;

	public UfDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Uf uf) {
		em.persist(uf);
	}
	
	public Uf buscarPorSigla (String sigla){
		try {
			TypedQuery<Uf> query = em.createQuery ("SELECT u FROM Uf u WHERE u.sigla = :pSigla", Uf.class);
			query.setParameter("pSigla", sigla);
			return query.getSingleResult();			
		}catch (NoResultException e) {
			return null;
		}
	}

	public Uf buscarPorNome(String nome) {
		try {
			TypedQuery<Uf> query = em.createQuery("SELECT u FROM Uf u WHERE u.nome = :nomeUf", Uf.class);
			query.setParameter("nomeUf", nome);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}
	
	}

}
