package com.servicos;

import javax.persistence.EntityManager;

import com.dao.BairroDao;
import com.dao.EmpresaDao;
import com.dao.FiscalizacaoDao;
import com.dao.MunicipioDao;
import com.dao.UfDao;
import com.model.Bairro;
import com.model.Empresa;
import com.model.Fiscalizacao;
import com.model.Municipio;
import com.model.Uf;
import com.model.csv.FiscalizacaoCsv;

public class FiscalizacaoProcessador implements  ArquivoFiscalizacaoProcessador  {
	EntityManager em;
	private UfDao ufDao;
	private MunicipioDao municipioDao;
	private BairroDao bairroDao;
	private EmpresaDao empresaDao;
	private FiscalizacaoDao fiscalizacaoDao;		
	
	public FiscalizacaoProcessador(EntityManager em) {
		this.em=em;
		this.empresaDao= new EmpresaDao(em);
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
		this.bairroDao= new BairroDao(em);
		this.empresaDao = new EmpresaDao(em);
		this.fiscalizacaoDao = new FiscalizacaoDao(em);
		
	}
	
	public void processa(FiscalizacaoCsv fiscalizacaoCsv) {
			//FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(fiscalizacaoCsv);		
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
				return;
			}
			
			Fiscalizacao fiscalizacao = fiscalizacaoDao.busca(empresa, fiscalizacaoCsv.getData());
			if(fiscalizacao == null) {
				fiscalizacao = new Fiscalizacao();
				fiscalizacao.setBairro(bairro);
				fiscalizacao.setData(fiscalizacaoCsv.getData());
				fiscalizacao.setEmpresa(empresa);
				fiscalizacao.setMunicipio(municipio);
				fiscalizacao.setUf(uf);			
				fiscalizacaoDao.inserir(fiscalizacao);  
			} 
	}

}
