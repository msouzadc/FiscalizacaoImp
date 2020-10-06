package br.edu.fsma.servicos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LeitorArquivo {
	
	public void executa (String arquivo, Processador processador) throws IOException {
		BufferedReader conteudoCsv = null;
		String linha;
		try {
			conteudoCsv = new BufferedReader(new FileReader(arquivo));
			while ((linha = conteudoCsv.readLine()) != null){
				processador.processa(linha);
			}
		}catch (Exception e) {
				System.out.println(e);
				System.exit(0);
		}
	}
	
}
	
	
	/*
	try {
		InputStream inputStrem = new FileInputStream(arquivo);
		InputStreamReader reader = new InputStreamReader(inputStrem);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
		while (linha !=null) {
			processador.processa(linha);
			linha = br.readLine();
		}
		br.close();
		
	}catch (FileNotFoundException e){
		e.printStackTrace();
		
	}catch (Exception e) {
		e.printStackTrace();
	}

}*/
