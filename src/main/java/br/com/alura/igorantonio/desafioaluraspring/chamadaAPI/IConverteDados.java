package br.com.alura.igorantonio.desafioaluraspring.chamadaAPI;

import java.util.List;

public interface IConverteDados {

    public <T> List<T> converteDadosArrayDeObjetos(String json, Class<T[]> classe );
    public <T> T obterDados(String json, Class<T> classe);
}
