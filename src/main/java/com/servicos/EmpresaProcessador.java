package com.servicos;

import javax.persistence.EntityManager;

import com.dao.BairroDao;
import com.dao.EmpresaDao;
import com.dao.MunicipioDao;
import com.dao.UfDao;
import com.model.Bairro;
import com.model.Empresa;
import com.model.Municipio;
import com.model.Uf;
import com.model.csv.FiscalizacaoCsv;

public class EmpresaProcessador implements ArquivoFiscalizacaoProcessador {
	private EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;
	private BairroDao bairroDao;
	private EmpresaDao empresaDao;
	
	public EmpresaProcessador (EntityManager em) {
		this.em = em;
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
		this.bairroDao = new BairroDao(em);
		this.empresaDao = new EmpresaDao(em);
	}
	
	public void processa (FiscalizacaoCsv fiscalizacaoCsv) {
		if (fiscalizacaoCsv.isNaoValido()) {
			em.getTransaction().rollback();
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
			return;
		}
		
		Empresa empresa = empresaDao.busca(fiscalizacaoCsv.getCnpj());
		if (empresa == null) {
			empresa = new Empresa();
			empresa.setData(fiscalizacaoCsv.getData());
			empresa.setUf(uf);
			empresa.setMunicipio(municipio);
			empresa.setBairro(bairro);
			empresa.setRazaoSocial(fiscalizacaoCsv.getRazaoSocial());	
			empresaDao.inserir(empresa);
		}			
	}		
}

