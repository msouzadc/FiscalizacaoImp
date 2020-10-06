package br.edu.fsma.servicos;

import javax.persistence.EntityManager;

import br.edu.fsma.dao.MunicipioDao;
import br.edu.fsma.dao.UfDao;
import br.edu.fsma.modelo.Municipio;
import br.edu.fsma.modelo.Uf;
import br.edu.fsma.modelo.csv.EmpresaCsv;

public class MunicipioProcessador implements  ArquivoFiscalizacaoProcessador {
	private UfDao ufDao;
	private MunicipioDao municipioDao;
		
	public MunicipioProcessador(EntityManager em) {
		this.ufDao = new UfDao(em);
		this.municipioDao = new MunicipioDao(em);
	}
	
	@Override
	public void processa(EmpresaCsv empresaCsv) {
		Uf uf = ufDao.buscarPorNome(empresaCsv.getNomeUf());
		if (uf == null) {
			return;
		}
		
		Municipio municipio = municipioDao.buscarPorNome(uf, empresaCsv.getMunicipio());
		if (municipio == null){
			municipio = new Municipio();
			municipio.setNome(empresaCsv.getMunicipio());
			municipio.setUf(uf);
			municipioDao.inserir(municipio);
		}
	}
}

