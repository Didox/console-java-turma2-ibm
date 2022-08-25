package com.br.danilo.console.servicos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.br.danilo.console.interfaces.IEntidade;
import com.br.danilo.console.interfaces.IPersistencia;

public class PersistenciaCSV implements IPersistencia {
    private Class<?> tipo;
    public PersistenciaCSV(Class<?> tipo){
        this.tipo = tipo;
    }

    public void salvar(IEntidade entidade) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        List<IEntidade> entidades = this.Todos();
        entidades.add(entidade);
        try {
            FileWriter myWriter = new FileWriter(tipo.getName() + ".csv");
            String entidadesCSV = "";
            for (IEntidade iEntidade : entidades) {
                entidadesCSV += iEntidade.getNome() + ";\n";
            }
            myWriter.write(entidadesCSV);
            myWriter.close();
        } catch (IOException e) {
        }
    }

    public List<IEntidade> Todos() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<IEntidade> entidades = new ArrayList<IEntidade>();
        try {
            File myObj = new File(this.tipo.getName() + ".csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                var constru = this.tipo.getConstructor();
                final IEntidade entidade = (IEntidade)constru.newInstance();
                entidade.setNome(myReader.nextLine());
                entidades.add(entidade);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
        }

        return entidades != null ? entidades : new ArrayList<IEntidade>();
    }
}
