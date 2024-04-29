package br.com.alura.igorantonio.desafioaluraspring.chamadaAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class ConverterDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> converteDadosArrayDeObjetos(String json, Class<T[]> classe ) {
        try {
          T[] array = mapper.readValue(json, classe);
           return Arrays.asList(array);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public <T> T obterDados(String json, Class<T> classe) {
            try {
                return objectMapper.readValue(json, classe);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter JSON para objeto: " + e.getMessage(), e);
            }
        }

}
