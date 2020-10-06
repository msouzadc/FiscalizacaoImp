package br.edu.fsma.servicos;

import javax.persistence.EntityManager;

import br.edu.fsma.dao.BairroDao;
import br.edu.fsma.dao.EmpresaDao;
import br.edu.fsma.dao.FiscalizacaoDao;
import br.edu.fsma.dao.MunicipioDao;
import br.edu.fsma.dao.UfDao;
import br.edu.fsma.modelo.Bairro;
import br.edu.fsma.modelo.Empresa;
import br.edu.fsma.modelo.Fiscalizacao;
import br.edu.fsma.modelo.Municipio;
import br.edu.fsma.modelo.Uf;
import br.edu.fsma.modelo.csv.EmpresaCsv;

public class FiscalizacaoProcessador implements  ArquivoFiscalizacaoProcessador  {
	private EntityManager em;
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
	
	public void processa(EmpresaCsv empresaCsv) {
			
			if (empresaCsv.isNaoValido()) {
				em.getTransaction().rollback();
				return;
			}
			if (ValidadorCNPJ.isNotValid(empresaCsv.getCnpj())){
				return;
			}	
			
			String nomeUf = empresaCsv.getNomeUf();
			Uf uf = ufDao.buscarPorNome(nomeUf);
			if (uf == null) {
				return;
			}
			Municipio municipio = municipioDao.buscarPorNome(uf, empresaCsv.getMunicipio());
			if (municipio == null) {
				return;
			} 
			
			Bairro bairro = bairroDao.busca(municipio, empresaCsv.getBairro());
			if (bairro == null) {
				return;
			} 
			
			Empresa empresa = empresaDao.busca(empresaCsv.getCnpj());
			if (empresa == null) {
				return;
			}
			
			Fiscalizacao fiscalizacao = fiscalizacaoDao.busca(empresa, empresaCsv.getData());
			if(fiscalizacao == null) {
				fiscalizacao = new Fiscalizacao();
				fiscalizacao.setData(empresaCsv.getData());
				fiscalizacao.setEmpresa(empresa);
				empresa.setRazaoSocial(empresaCsv.getRazaoSocial());
				fiscalizacao.setBairro(bairro);
				fiscalizacao.setMunicipio(municipio);
				fiscalizacao.setUf(uf);	
				fiscalizacao.setCep(empresaCsv.getCep());  
			}
			
			if (empresaCsv.getData().isAfter(empresa.getData())) {
				fiscalizacao.setData(empresaCsv.getData());
				fiscalizacao.setEmpresa(empresa);
				empresa.setRazaoSocial(empresaCsv.getRazaoSocial());
				fiscalizacao.setBairro(bairro);
				fiscalizacao.setMunicipio(municipio);
				fiscalizacao.setUf(uf);	
				fiscalizacao.setCep(empresaCsv.getCep());
				empresaDao.atualizar(empresa);
			}
		fiscalizacaoDao.inserir(fiscalizacao);
	}

}
