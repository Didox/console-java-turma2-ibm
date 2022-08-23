package com.br.danilo.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogicaApplication {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
			
		System.out.println("===== [ Olá turma 2 Java IBM ] =======");
		
		System.out.println("Digite o seu nome:");
		String nome = reader.readLine();

		System.out.println("O nome digitado foi: " + nome);
		
		//SpringApplication.run(LogicaApplication.class, args);
	}

}
