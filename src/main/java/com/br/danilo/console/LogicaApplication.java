package com.br.danilo.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.danilo.console.models.Aluno;

@SpringBootApplication
public class LogicaApplication {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException, NumberFormatException, InterruptedException {
		while(true){
			limparTela();
			
			System.out.println("===== [ Cadastro dos alunos ] =======");
			System.out.println("Qual opção você deseja?");
			System.out.println("1 - Cadastrar aluno");
			System.out.println("2 - Mostrar relatório");
			System.out.println("3 - sair");

			int opcao = 0;

			try{
				opcao = Integer.parseInt(reader.readLine());
			}
			catch(Exception e) {}

			limparTela();

			var sair = false;
			switch(opcao){
				case 1:
					cadastroAluno();
					break;
				case 2:
					mostrarAlunos();
					break;
				case 3:
					sair = true;
					break;
				default:
					opcaoInvalida();
					break;
			}

			if(sair) break;
		}
		
		
		//SpringApplication.run(LogicaApplication.class, args);
	}

	private static void limparTela() {
		System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}

	private static void opcaoInvalida() throws IOException, NumberFormatException, InterruptedException {
		mensagem("Opção inválida");
	}

	private static void capturaNotasAluno(Aluno aluno) throws NumberFormatException, IOException, InterruptedException {
		System.out.println("Digite a nota do(a) " + aluno.getNome());
		if(aluno.getNotas() == null) aluno.setNotas(new ArrayList<Float>());

		try{
			aluno.getNotas().add(Float.parseFloat(reader.readLine()));
		}
		catch(Exception e){
			mensagem("Nota inválida");
			capturaNotasAluno(aluno);
		}

		try{
			System.out.println("Digite 1 para cadastrar mais notas ou 0 para finalizar o cadaastro");
			int opcao = Integer.parseInt(reader.readLine());
			if(opcao == 1) capturaNotasAluno(aluno);
			return;
		}
		catch(Exception e) {
			mensagem("Opção inválida, iniciando novo cadastro de nota");
			capturaNotasAluno(aluno);
		}
	}

	private static void mensagem(String string) throws InterruptedException {
		limparTela();
		System.out.println(string);
		espera(2);
		limparTela();
	}

	private static void espera(int secconds) throws InterruptedException {
		Thread.sleep(secconds*1000); 
	}

	private static void mostrarAlunos() throws InterruptedException {
		if(Aluno.all().size() == 0){
			mensagem("Nenhum aluno cadastrado");
			return;
		}

		System.out.println("======== [ Relatório de alunos ] ========");
		for (Aluno aluno : Aluno.all()) {
			System.out.println("Nome: "+ aluno.getNome());
			String notas = "";
			for (float nota : aluno.getNotas()) {
				notas += nota + ", ";
			}
			System.out.println("Notas: " + notas);
			System.out.println("Média: " + aluno.media());
			System.out.println("Situação: " + aluno.situacao());
			System.out.println("-------------------------------");
		}

		espera(8);
		limparTela();
	}

	private static void cadastroAluno() throws NumberFormatException, IOException, InterruptedException {
		var aluno  = new Aluno();
		System.out.println("Digite o nome do aluno");
		aluno.setNome(reader.readLine());

		capturaNotasAluno(aluno);

		aluno.savar();

		mensagem("Aluno cadastrado com sucesso!");
	}

}
