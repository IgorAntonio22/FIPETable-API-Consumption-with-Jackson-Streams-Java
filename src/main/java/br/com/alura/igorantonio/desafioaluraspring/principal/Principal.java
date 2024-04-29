package br.com.alura.igorantonio.desafioaluraspring.principal;

import br.com.alura.igorantonio.desafioaluraspring.chamadaAPI.ChamarAPI;
import br.com.alura.igorantonio.desafioaluraspring.chamadaAPI.ConverterDados;
import br.com.alura.igorantonio.desafioaluraspring.model.Modelo;
import br.com.alura.igorantonio.desafioaluraspring.model.ResponseModelos;
import br.com.alura.igorantonio.desafioaluraspring.model.Marca;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);

    private ChamarAPI consumoApi = new ChamarAPI();

    private ConverterDados conversor = new ConverterDados();

    private String URI = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenuDeOpcoes() {

        System.out.println("***************");
        System.out.println("  CARROS\n");
        System.out.println("  MOTOS\n");
        System.out.println("  CAMINHOES\n");

        System.out.println("Escolha um tipo de veículo:\n");

        var nomeDoVeiculo = scanner.nextLine();
        var json = consumoApi.obterDadosApi(URI + nomeDoVeiculo.toLowerCase() +"/marcas");
        List<Marca> dados = conversor.converteDadosArrayDeObjetos(json, Marca[].class);

        Map<Integer, String> mapaVeiculos = dados.stream()
                .collect(Collectors.toMap(Marca::codigo, Marca::nome));

        for(Map.Entry<Integer, String> entry : mapaVeiculos.entrySet()) {
            Integer codigo = entry.getKey();
            String nome = entry.getValue();
            System.out.println("Código: " + codigo + ", Marca: " + nome);
        }

        System.out.println("Informe o código da marca para consulta: ");

        var codigoDaMarca = scanner.nextLine();
        var json2 = consumoApi.obterDadosApi(URI + nomeDoVeiculo.toLowerCase() +"/marcas/"
                + codigoDaMarca + "/modelos");
        ResponseModelos responseModelos = conversor.obterDados(json2, ResponseModelos.class);

        System.out.println(responseModelos);

        List<Modelo> modelos = new ArrayList<Modelo>();

        for(Modelo modelo : responseModelos.modelos()) {
            modelos.add(modelo);
        }
        System.out.println(modelos);

        Map<Integer, String> mapaModelos = modelos.stream()
                .collect(Collectors.toMap(Modelo::codigo, Modelo::nome));

        for(Map.Entry<Integer, String> entry: mapaModelos.entrySet()) {
            System.out.println("Código do modelo: " + entry.getKey() +  " Nome do modelo: " + entry.getValue());
        }

        System.out.println("Digite um trecho do nome do veículo para consultar: ");

        String trechoDoModeloDigitado = scanner.nextLine();




    }
}
