package com.br.danilo.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogicaApplication {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
			
		System.out.println("===== [ Olá turma 2 Java IBM ] =======");
		
		List<String> nomes = new ArrayList<>();
		for(int i=1; i<=5; i++){
			System.out.println("Digite o nome de número " + i + " :");
			String nome = reader.readLine();
			nomes.add(nome);
		}
		
		for(int i=0; i<nomes.size(); i++){
			System.out.println("O nome " + (i+1) + " digitado foi: " + nomes.get(i));
		}

		//SpringApplication.run(LogicaApplication.class, args);
	}

}
