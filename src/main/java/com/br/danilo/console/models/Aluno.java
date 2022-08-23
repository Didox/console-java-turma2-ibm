package com.br.danilo.console.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Aluno {
	private static List<Aluno> alunos = new ArrayList<>();

    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    private List<Float> notas;
    public List<Float> getNotas() {
        return notas;
    }
    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    public String situacao(){
        var media = this.media();
        if(media < 5) return "Reprovado";
        else if(media >=5 && media < 7) return "Recuperação";
        else return "Aprovado";
    }

    public float media(){
        float mediaCalculada = (float) 0.0;

        if(this.notas != null){
            for (Float nota : this.notas) {
                mediaCalculada += nota;
            }

            mediaCalculada = mediaCalculada / this.notas.size();
        }

        return mediaCalculada;
    }
    public void savar() {
		Aluno.alunos.add(this);
        try {
            FileWriter myWriter = new FileWriter("alunos.json");
            String alunosJson = new Gson().toJson(Aluno.alunos);
            myWriter.write(alunosJson);
            myWriter.close();
        } catch (IOException e) {
        }
    }

    public static List<Aluno> all(){

        if(Aluno.alunos == null || Aluno.alunos.size() == 0){
            try {
                File myObj = new File("alunos.json");
                Scanner myReader = new Scanner(myObj);
                String alunosJson = "";
                while (myReader.hasNextLine()) {
                    alunosJson += myReader.nextLine();
                }
                myReader.close();

                Aluno.alunos = new Gson().fromJson(alunosJson,  new TypeToken<List<Aluno>>(){}.getType());
            } catch (FileNotFoundException e) {
            }
        }

        return Aluno.alunos;
    }

    @Override    
    public String toString() {    
        return this.nome;    
    }  

}
