package br.edu.fsma.modelo.csv;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class EmpresaCsv {
	
	private String campos[];
	private boolean valido;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public EmpresaCsv (String linha) {
		valido = false;
		if (linha == null) {
			return;
		}
		campos = linha.split(";");
		if (campos.length !=9) {
			return;
		}
		if (getBairro().equals("SEM INFORMAÇÃO")) {
			return;
		}
		valido = true;
	}

	public LocalDate getData() {
		String dateSections[] = campos[1].split("/");
		String year = dateSections[0];
		String month = dateSections[1];
		String date = "15-"+month+"-"+year;
		
		LocalDate dateParsed = LocalDate.parse(date, formatter);
		
		return dateParsed;
	}

	public String getCnpj() {
		return campos[2];
	}

	public String getRazaoSocial() {
		return campos[3].toUpperCase().trim();
	}

	public String getLogradouro() {
		return campos[4];
	}

	public String getCep() {
		return campos[5];
	}

	public String getBairro() {
		return campos[6].toUpperCase().trim();
	}

	public String getMunicipio() {
		return campos[7].toUpperCase().trim();
	}

	public String getNomeUf() {
		return campos[8].toUpperCase().trim();
	}

	public boolean isNaoValido() {
		return !valido;
	}
	
}
