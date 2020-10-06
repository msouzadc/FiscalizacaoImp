package br.edu.fsma.servicos;

import javax.persistence.EntityManager;

import br.edu.fsma.dao.BairroDao;
import br.edu.fsma.dao.EmpresaDao;
import br.edu.fsma.dao.MunicipioDao;
import br.edu.fsma.dao.UfDao;
import br.edu.fsma.modelo.Bairro;
import br.edu.fsma.modelo.Empresa;
import br.edu.fsma.modelo.Municipio;
import br.edu.fsma.modelo.Uf;
import br.edu.fsma.modelo.csv.EmpresaCsv;


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
	
	public void processa (EmpresaCsv empresaCsv) {
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
				empresa = new Empresa();
				empresa.setData(empresaCsv.getData());
				empresa.setUf(uf);
				empresa.setMunicipio(municipio);
				empresa.setBairro(bairro);
				empresa.setRazaoSocial(empresaCsv.getRazaoSocial());	
				empresa.setCnpj(empresaCsv.getCnpj());
				empresa.setData(empresaCsv.getData());
				
				empresaDao.inserir(empresa); 
		}		
	}		
}

