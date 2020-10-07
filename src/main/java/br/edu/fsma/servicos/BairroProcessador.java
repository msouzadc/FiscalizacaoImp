package br.edu.fsma.servicos;

import javax.persistence.EntityManager;

import br.edu.fsma.dao.BairroDao;
import br.edu.fsma.dao.MunicipioDao;
import br.edu.fsma.dao.UfDao;
import br.edu.fsma.modelo.Bairro;
import br.edu.fsma.modelo.Municipio;
import br.edu.fsma.modelo.Uf;
import br.edu.fsma.modelo.csv.EmpresaCsv;

public class BairroProcessador implements ArquivoFiscalizacaoProcessador {
	
	private MunicipioDao municipioDao;
	private BairroDao bairroDao;
	private UfDao ufDao;
	
	public BairroProcessador(EntityManager em) {
		
		this.bairroDao = new BairroDao(em);
		this.municipioDao = new MunicipioDao(em);
		this.ufDao = new UfDao(em);
	}
	
	public void processa(EmpresaCsv empresaCsv) {
		
		if (empresaCsv.isNaoValido()) {
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
			bairro = new Bairro();
			bairro.setMunicipio(municipio);
			bairro.setNome(empresaCsv.getBairro());
			bairroDao.inserir(bairro);
		}

	}	

}
