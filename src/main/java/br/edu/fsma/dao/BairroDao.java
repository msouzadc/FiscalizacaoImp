package br.edu.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.modelo.Bairro;
import br.edu.fsma.modelo.Municipio;

public class BairroDao {
	
	private EntityManager em;

	public BairroDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Bairro bairro) {
		em.persist(bairro);
	}

	public Bairro busca(Municipio municipio, String nomeBairro) {
		try {
			TypedQuery<Bairro> query = em.createQuery("SELECT b FROM Bairro b WHERE b.nome = :Bairro and b.municipio = :municipio", Bairro.class);
			query.setParameter("Bairro", nomeBairro);
			query.setParameter("municipio", municipio);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}	
	}
	

}
