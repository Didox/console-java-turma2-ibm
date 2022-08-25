package com.br.danilo.console.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IPersistencia {
    public void salvar(IEntidade entidade) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    public List<IEntidade> Todos() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
