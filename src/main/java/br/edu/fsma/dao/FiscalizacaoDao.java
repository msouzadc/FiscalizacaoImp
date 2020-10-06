package br.edu.fsma.dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.fsma.modelo.Empresa;
import br.edu.fsma.modelo.Fiscalizacao;

public class FiscalizacaoDao {
	private EntityManager em;
	
	public FiscalizacaoDao (EntityManager em) {
		this.em=em;
	}
	
	public void inserir (Fiscalizacao fiscalizacao) {
		em.persist(fiscalizacao);
	}

	 public Fiscalizacao busca(Empresa empresa, LocalDate data) {
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
