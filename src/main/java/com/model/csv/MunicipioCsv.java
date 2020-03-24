package com.model.csv;

public class MunicipioCsv {
	private String campos [];
	
	public MunicipioCsv (String linha) {
		System.out.println(linha);
		campos = linha.split(":");
	}
	
	public String getNome() {
		return campos[0];
	}
	
	public String getUf() {
		return campos[1];
	}

}
