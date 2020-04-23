package com.model.csv;

public class FiscalizacaoCsv {
	
	private String data;
	private String cnpj;
	private String razaoSocial;
	private String logradouro;
	private String cep;
	private String bairro;
	private String municipio;
	private String nomeUf;
	private boolean valido;
	
	public FiscalizacaoCsv (String linha) {
		valido = false;
		if (linha == null) {
			return;
		}
		String campos [] = linha.split(";");
		if (campos.length !=9) {
			return;
		}
		data = campos[1];
		cnpj = campos[2];
		razaoSocial = campos[3].toUpperCase().trim();
		logradouro = campos[4];
		cep = campos[5];
		bairro = campos[6].toUpperCase().trim();
		municipio = campos[7].toUpperCase().trim();
		nomeUf = campos[8].toUpperCase().trim();
		valido = true;
	}

	public String getData() {
		return data;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial.toUpperCase().trim();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getNomeUf() {
		return nomeUf;
	}

	public boolean isNaoValido() {
		return !valido;
	}
}
