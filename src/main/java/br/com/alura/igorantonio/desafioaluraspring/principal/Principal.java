package br.com.alura.igorantonio.desafioaluraspring.principal;

import br.com.alura.igorantonio.desafioaluraspring.chamadaAPI.ChamarAPI;
import br.com.alura.igorantonio.desafioaluraspring.chamadaAPI.ConverterDados;
import br.com.alura.igorantonio.desafioaluraspring.model.*;

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

        var trechoDoModeloDigitado = scanner.nextLine();

        trechoDoModeloDigitado.toLowerCase();
        //entrySet() transforma o mapa em um Set, serve para iterarmos ou fazermos um stream()
        Map<Integer, String> mapaFiltrado = mapaModelos.entrySet().stream()
                .filter(e -> e.getValue().toLowerCase().contains(trechoDoModeloDigitado))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Imprimindo o mapa filtrado
        for(Map.Entry<Integer, String> entry : mapaFiltrado.entrySet()) {
            Integer codigo = entry.getKey();
            String nome = entry.getValue();
            System.out.println("Código: " + codigo + ", Modelo: " + nome);
        }

        System.out.println("Digite o código do modelo para consulta de valores: ");
        var codigoDoVeiculo = scanner.nextLine();

        var json3 = consumoApi.obterDadosApi(URI + nomeDoVeiculo.toLowerCase() +"/marcas/"
                + codigoDaMarca + "/modelos/" + codigoDoVeiculo + "/anos");
        List<Ano> anos = conversor.converteDadosArrayDeObjetos(json3, Ano[].class);
        System.out.println(anos);

        List<Valor> listaDeValores = new ArrayList<Valor>();

        for(Ano ano: anos) {
            var json4 = consumoApi.obterDadosApi(URI + nomeDoVeiculo.toLowerCase() +"/marcas/"
                    + codigoDaMarca + "/modelos/" + codigoDoVeiculo + "/anos/" + ano.codigo());
            Valor valoresVeiculosPorAno = conversor.obterDados(json4, Valor.class);
            listaDeValores.add(valoresVeiculosPorAno);
        }

        for(Valor valor : listaDeValores) {
            System.out.println(valor);
        }
    }
}
