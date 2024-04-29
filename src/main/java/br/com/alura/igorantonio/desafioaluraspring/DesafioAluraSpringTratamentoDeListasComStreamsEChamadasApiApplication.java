package br.com.alura.igorantonio.desafioaluraspring;

import br.com.alura.igorantonio.desafioaluraspring.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioAluraSpringTratamentoDeListasComStreamsEChamadasApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DesafioAluraSpringTratamentoDeListasComStreamsEChamadasApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenuDeOpcoes();
    }
}
