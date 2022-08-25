package com.br.danilo.console.servicos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.br.danilo.console.interfaces.IEntidade;
import com.br.danilo.console.interfaces.IPersistencia;
import com.br.danilo.console.models.Aluno;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PersistenciaJson implements IPersistencia {
    private Class<?> tipo;
    public PersistenciaJson(Class<?> tipo){
        this.tipo = tipo;
    }

    public void salvar(IEntidade entidade){
        List<IEntidade> entidades = this.Todos();
        entidades.add(entidade);
        try {
            FileWriter myWriter = new FileWriter(tipo.getName() + ".json");
            String entidadesJson = new Gson().toJson(entidades);
            myWriter.write(entidadesJson);
            myWriter.close();
        } catch (IOException e) {
        }
    }

    public List<IEntidade> Todos() {
        List<IEntidade> entidades = new ArrayList<IEntidade>();
        try {
            File myObj = new File(tipo.getName() + ".json");
            Scanner myReader = new Scanner(myObj);
            String entidadesJson = "";
            while (myReader.hasNextLine()) {
                entidadesJson += myReader.nextLine();
            }
            myReader.close();

            entidades = new Gson().fromJson(entidadesJson,  new TypeToken<List<Aluno>>(){}.getType());
        } catch (FileNotFoundException e) {
        }

        return entidades != null ? entidades : new ArrayList<IEntidade>();
    }
}
