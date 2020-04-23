package com.servicos;

import javax.persistence.EntityManager;

import com.dao.MunicipioDao;
import com.dao.UfDao;
import com.model.Municipio;
import com.model.Uf;
import com.model.csv.FiscalizacaoCsv;
import com.model.csv.MunicipioCsv;

public class MunicipioProcessador implements Processador, ArquivoFiscalizacaoProcessador {
	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;
		
	public MunicipioProcessador(EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
	}
	
	public void processa(String linha) {
		try {
			em.getTransaction().begin();
			MunicipioCsv municipioCsv = new MunicipioCsv(linha);
			
			Uf uf = ufDao.buscarPorSigla(municipioCsv.getUf());
			
			if (uf != null){
				Municipio municipio = new Municipio();
				municipio.setNome(municipioCsv.getNome());
				municipio.setUf(uf);
				municipioDao.inserir(municipio);
			}
			em.getTransaction().commit();
		}catch (Exception e){
			System.out.println("Erro na Transação \n" + e.getMessage());
			em.getTransaction().rollback();
		}
	}

	@Override
	public void processa(FiscalizacaoCsv fiscalizacaoCsv) {
		Uf uf = ufDao.buscarPorNome(fiscalizacaoCsv.getNomeUf());
		if (uf == null) {
			return;
		}
		
		Municipio municipio = municipioDao.buscarPorNome(uf, fiscalizacaoCsv.getMunicipio());
		if (municipio == null){
			municipio = new Municipio();
			municipio.setNome(fiscalizacaoCsv.getMunicipio());
			municipio.setUf(uf);
			municipioDao.inserir(municipio);
		}
	}
}

