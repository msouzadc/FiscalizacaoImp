package br.edu.fsma.servicos;

public class ValidadorCNPJ {

	public static boolean isValid(String string) {
		String cnpj = tirandoCaracteresDesnecessarios(string);
		if(cnpjNaoValido(cnpj)) {
			return false;
		}
		if (cnpj.length() != 14) {
			return false;
		}
		
		String digito13 = calculaPrimeiroDigito(cnpj);
		String digito14 = calculaSegundoDigito(cnpj);
		
		return verificaOsDigitos(cnpj, digito13, digito14);
		
	}
	
	public static boolean isNotValid(String string) {
		return !isValid(string);
	}

	private static boolean verificaOsDigitos(
			String cnpj,
			String digito13,
			String digito14
			) {
		
		return (
				(digito13.equals(cnpj.substring(12,13)))
				&&
				(digito14.equals(cnpj.substring(13,14)))
				);
	}

	private static String calculaSegundoDigito(String cnpj) {
		int soma = 0;
		int peso = 2;
		for (int i = 12; i >= 0; i--) {
			int digito = Integer.parseInt( cnpj.substring(i, i+1) ); 
			soma += (digito * peso);
			peso++;
			if (peso == 10)
				peso = 2;
		}

		int resto = soma % 11;
		if ((resto == 0) || (resto == 1))
			return "0";
		else
			return  String.valueOf(11 - resto);
		
	}

	private static String calculaPrimeiroDigito(String cnpj) {
		int peso = 2;
		int soma = 0;
		
		for(int i=11; i>=0;i--) {
			
			int digito = Integer.parseInt(cnpj.substring(i,i+1));
			soma += (digito * peso);
			peso ++;
			if( peso == 10) {
				peso =2;
			}
			
		}
		
		int resto = soma % 11;
		if((resto ==0) || (resto ==1)) {
			return "0";
		}else {
			return String.valueOf(11 - resto);
		}
		
	}

	private static boolean cnpjNaoValido(String cnpj) {
		return cnpj.equals("00000000000000") || 
				cnpj.equals("11111111111111") || 
				cnpj.equals("22222222222222") || 
				cnpj.equals("33333333333333") || 
				cnpj.equals("44444444444444") || 
				cnpj.equals("55555555555555") || 
				cnpj.equals("66666666666666") || 
				cnpj.equals("77777777777777") || 
				cnpj.equals("88888888888888") ||
				cnpj.equals("99999999999999");
	}

	public static String tirandoCaracteresDesnecessarios(String string) {
		return string
				.trim()
				.replace(".","")
				.replace("/","")
				.replace("-","")
				.replace("�", "")
				.replace("ç","");
	}
	
}
