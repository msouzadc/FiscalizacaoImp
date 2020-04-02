package com.model.csv;

public class MunicipioCsv {
	private String nome;
	private String sigla;
	
	public MunicipioCsv (String linha) {
		String campos [] = linha.split(";");
		nome = campos[3];
		sigla = campos [0];
	}
	
	public String getNome() {
		return nome.toUpperCase();
	}
	
	public String getUf() {
		return sigla.toUpperCase().trim();
	}

}
