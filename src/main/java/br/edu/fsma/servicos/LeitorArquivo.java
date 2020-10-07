package br.edu.fsma.servicos;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeitorArquivo {
	
	public void executa (String arquivo, Processador processador) {
		try {
			BufferedReader conteudoCsv = new BufferedReader(new FileReader(arquivo));
			String linha = conteudoCsv.readLine();
			while (linha != null) {
				processador.processa(linha);
				linha = conteudoCsv.readLine();
			}
			conteudoCsv.close();
		} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
		}
	}
	
}
