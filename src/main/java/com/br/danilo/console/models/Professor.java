package com.br.danilo.console.models;

import java.util.List;

import com.br.danilo.console.interfaces.IEntidade;
import com.br.danilo.console.servicos.PersistenciaJson;

public class Professor implements IEntidade {
    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void salvar() {
		new PersistenciaJson(this.getClass()).salvar(this);
    }

    public static List<IEntidade> all(){
        return new PersistenciaJson(new Aluno().getClass()).Todos();
    }

    @Override    
    public String toString() {    
        return this.nome;    
    }  

}
