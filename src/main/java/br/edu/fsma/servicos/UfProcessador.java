package br.edu.fsma.servicos;

import javax.persistence.EntityManager;

import br.edu.fsma.dao.UfDao;
import br.edu.fsma.modelo.Uf;
import br.edu.fsma.modelo.csv.UfCsv;

public class UfProcessador implements Processador {
	private EntityManager em;
	private UfDao ufDao;
		
	public UfProcessador(EntityManager em) {
		this.em=em;
		this.ufDao= new UfDao(em);
	}

	@Override
	public void processa(String linha) {
		try {
			em.getTransaction().begin();
			UfCsv ufCsv = new UfCsv(linha);
			Uf uf = new Uf();
			uf.setNome(ufCsv.getNome());
			uf.setSigla(ufCsv.getSigla());			
			ufDao.inserir(uf);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Erro na Transação \n" + e.getMessage());
			em.getTransaction().rollback();
		}
	}
}
