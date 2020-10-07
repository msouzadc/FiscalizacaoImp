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
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b  ");
		sb.append(" FROM Bairro b ");
		sb.append("WHERE ");
		sb.append("   b.nome = :Bairro ");
		sb.append("   and  b.municipio = :municipio");
		
		try {
			TypedQuery<Bairro> query = em.createQuery(sb.toString(), Bairro.class);
			query.setParameter("Bairro", nomeBairro);
			query.setParameter("municipio", municipio);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}	
	}
	

}
