package com.main;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.conexao.JPAUtil;
import com.servicos.ImportadorProcessador;
import com.servicos.LeitorArquivo;
import com.servicos.MunicipioProcessador;
import com.servicos.UfProcessador;

public class Main {
	
	public static void main(String[] args) throws IOException  {
		System.out.println("Inicio Importação.........................");		
		String arquivoUf = "C:\\Users\\Marcela\\eclipse-workspace\\estados2.txt";
		String arquivoMunicipio = "C:\\Users\\Marcela\\eclipse-workspace\\municipios.csv";
		String arquivoFiscalizacao = "C:\\Users\\Marcela\\eclipse-workspace\\arquivo.csv";

		EntityManager em = JPAUtil.getEntityManager();
		
		LeitorArquivo leitor = new LeitorArquivo();
		
		leitor.executa(arquivoUf, new UfProcessador(em));
		leitor.executa(arquivoMunicipio, new MunicipioProcessador(em));
		leitor.executa(arquivoFiscalizacao, new ImportadorProcessador(em));		
		
		System.out.println("Fim da importação.........................");
		System.exit(0);
	}
}