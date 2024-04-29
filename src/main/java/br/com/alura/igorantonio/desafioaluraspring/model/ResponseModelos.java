package br.com.alura.igorantonio.desafioaluraspring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseModelos (
        @JsonAlias("anos") List<Ano> anos,
        @JsonAlias("modelos") List<Modelo> modelos
) {
}