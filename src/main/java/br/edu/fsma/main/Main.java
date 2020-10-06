package br.edu.fsma.main;

import java.io.IOException;

import javax.persistence.EntityManager;

import br.edu.fsma.conexao.JPAUtil;
import br.edu.fsma.servicos.ImportadorProcessador;
import br.edu.fsma.servicos.LeitorArquivo;
import br.edu.fsma.servicos.UfProcessador;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		System.out.println("Inicio Importação.........................");		
		String caminho = "C:\\Users\\Marcela\\eclipse-workspace\\arquivoProducao\\";
		//String caminho = "C:\\Users\\Marcela\\eclipse-workspace\\arquivoDesv\\";
		String arquivoUf = caminho + "estados2.txt";
		String arquivoFiscalizacao = caminho + "fiscalizacao.csv";

		EntityManager em = JPAUtil.getEntityManager();
		
		LeitorArquivo leitor = new LeitorArquivo();
		
		leitor.executa(arquivoUf, new UfProcessador(em));
		leitor.executa(arquivoFiscalizacao, new ImportadorProcessador(em));		
		
		System.out.println("Fim da importação.........................");
		System.exit(0);
	}
}