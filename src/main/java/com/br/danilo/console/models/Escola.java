package com.br.danilo.console.models;

import com.br.danilo.console.interfaces.IEntidade;

public class Escola implements IEntidade {
    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    private String endereco;
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    @Override    
    public String toString() {    
        return this.nome;    
    }  

}
