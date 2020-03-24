package com.servicos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LeitorArquivo {
	
	public void executa(String arquivo, Processador processador) {

		try {
			InputStream inputStream = new FileInputStream(arquivo);
			InputStreamReader importStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader br = new BufferedReader(importStreamReader);
			String linha = br.readLine();	
			
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();		
			}				
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
