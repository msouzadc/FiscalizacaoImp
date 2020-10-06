package br.edu.fsma.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fsma.modelo.csv.EmpresaCsv;

public class ImportadorProcessador implements Processador {
	
	private List<ArquivoFiscalizacaoProcessador> processadores = new ArrayList<>();
	private EntityManager em;
	
	public ImportadorProcessador(EntityManager em) {
		this.em = em;
		processadores.add(new MunicipioProcessador(em));
		processadores.add(new BairroProcessador(em));
		processadores.add(new EmpresaProcessador(em));
		processadores.add(new FiscalizacaoProcessador(em));
	}

	public void processa(String linha) {
		try {
			em.getTransaction().begin();
			EmpresaCsv empresaCsv = new EmpresaCsv(linha);
			for (ArquivoFiscalizacaoProcessador processador : processadores) {
				processador.processa(empresaCsv);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro na Transação \n" + e.getMessage());
			em.getTransaction().rollback();
		}
	}
}
