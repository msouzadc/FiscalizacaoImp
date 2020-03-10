package com.main;

import java.io.IOException;

import com.servicos.LeitorArquivo;

public class Main {
	public static void main(String[] args) throws IOException  {
			String arquivo = "C:\\Users\\Marcela\\eclipse-workspace\\estados.txt";
			LeitorArquivo leitor = new LeitorArquivo();
			leitor.executa(arquivo);
			System.exit(0);
	}
}
