package com.main;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.conexao.JPAUtil;
import com.servicos.LeitorArquivo;
import com.servicos.UfProcessador;

public class Main {
	public static void main(String[] args) throws IOException  {
		String arquivoUf = "C:\\Users\\Marcela\\eclipse-workspace\\estados.txt";
		System.out.println("Inicio Importação");
		EntityManager em = JPAUtil.getEntityManager();
		LeitorArquivo leitor = new LeitorArquivo();
		leitor.executa(arquivoUf, new UfProcessador (em));
		System.exit(0);
	}
}
