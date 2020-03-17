package com.servicos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.persistence.EntityManager;
import com.conexao.JPAUtil;

public class LeitorArquivo {
	public void executa(String arquivo) {

		try {
			InputStream inputStream = new FileInputStream(arquivo);
			InputStreamReader importStreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(importStreamReader);
			String linha = br.readLine();
			
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
			}
			
			br.close();
			em.getTransaction().commit(); em.close();
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
