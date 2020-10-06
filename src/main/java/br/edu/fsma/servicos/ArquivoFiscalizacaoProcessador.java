package br.edu.fsma.servicos;

import br.edu.fsma.modelo.csv.EmpresaCsv;

public interface ArquivoFiscalizacaoProcessador {
	
	void processa(EmpresaCsv empresaCsv);
	
}
