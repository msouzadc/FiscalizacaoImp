package com.servicos;

import javax.persistence.EntityManager;

import com.dao.BairroDao;
import com.dao.MunicipioDao;
import com.dao.UfDao;
import com.model.Bairro;
import com.model.Municipio;
import com.model.Uf;
import com.model.csv.FiscalizacaoCsv;

public class BairroProcessador implements ArquivoFiscalizacaoProcessador {
	
	private EntityManager em;
	private MunicipioDao municipioDao;
	private BairroDao bairroDao;
	private UfDao ufDao;
	
	public BairroProcessador(EntityManager em) {
		this.em = em;
		this.bairroDao = new BairroDao(em);
		this.municipioDao = new MunicipioDao(em);
		this.ufDao = new UfDao(em);
	}
	
	public void processa(FiscalizacaoCsv fiscalizacaoCsv) {
		if (fiscalizacaoCsv.isNaoValido()) {
			return;
		}
		
		String nomeUf = fiscalizacaoCsv.getNomeUf();
		Uf uf = ufDao.buscarPorNome(nomeUf);
		if (uf == null) {
			return;
		}
		
		Municipio municipio = municipioDao.buscarPorNome(uf, fiscalizacaoCsv.getMunicipio());
		if (municipio == null) {
			return;
		}
		Bairro bairro = bairroDao.busca(municipio, fiscalizacaoCsv.getBairro());
		if (bairro == null) {
			bairro = new Bairro();
			bairro.setMunicipio(municipio);
			bairro.setNome(fiscalizacaoCsv.getBairro());
			bairroDao.inserir(bairro);
		}

	}	

}
