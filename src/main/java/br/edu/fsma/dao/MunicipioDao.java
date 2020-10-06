package br.edu.fsma.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.modelo.Municipio;
import br.edu.fsma.modelo.Uf;

public class MunicipioDao {
	private EntityManager em;

	public MunicipioDao(EntityManager em) {
		this.em = em;
	}

	public void inserir(Municipio municipio) {
		em.persist(municipio);
	}
	
	public Municipio buscarPorNome(Uf uf, String nomeMunicipio) {
		try {
			TypedQuery<Municipio> query = em.createQuery("SELECT u FROM Municipio u WHERE u.nome = :nomeMunicipio and u.uf = :uf", Municipio.class);
			query.setParameter("nomeMunicipio", nomeMunicipio);
			query.setParameter("uf", uf);
			return  query.getSingleResult();
			 
		} catch (NoResultException e) {
			return null;
		}
	}
}
