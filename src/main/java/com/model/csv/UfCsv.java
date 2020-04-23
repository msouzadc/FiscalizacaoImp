package com.model.csv;

public class UfCsv {
	
	private String campos[];

	public UfCsv(String linha) {
		campos = linha.split(",");
	}

	public String getNome() {
		return campos[0].toUpperCase().trim();
	}

	public String getSigla() {

		return campos[1].trim();
	}

}
