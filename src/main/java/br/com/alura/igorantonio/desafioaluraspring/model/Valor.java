package br.com.alura.igorantonio.desafioaluraspring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Valor (@JsonAlias("TipoVeiculo") Integer tipoVeiculo,
                    @JsonAlias("Valor") String valor,
                    @JsonAlias("Marca") String marca,
                    @JsonAlias("Modelo") String modelo,
                    @JsonAlias("AnoModelo") Integer anoModelo,
                    @JsonAlias("Combustivel") String combustivel,
                    @JsonAlias("MesReferencia") String mesReferencia
) {
}
