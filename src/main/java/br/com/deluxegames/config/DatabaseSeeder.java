package br.com.deluxegames.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.deluxegames.DeluxeGames.model.Modelo;
import br.com.deluxegames.DeluxeGames.model.Movimentacao;
import br.com.deluxegames.DeluxeGames.repository.ModeloRepository;
import br.com.deluxegames.DeluxeGames.repository.MovimentacaoRepository;


@Configuration
public class DatabaseSeeder implements CommandLineRunner {

@Autowired
ModeloRepository modeloRepository;

@Autowired
MovimentacaoRepository movimentacaoRepository;

@Override
public void run(String... args) throws Exception {

modeloRepository.saveAll(
        List.of(
                Modelo.builder().id(1L).nome("Ps5").icone("gamepad").build(),
                Modelo.builder().id(2L).nome("Xbox").icone("joystick").build(),
                Modelo.builder().id(3L).nome("Computador").icone("swords").build()));

movimentacaoRepository.saveAll(
        List.of(
                Movimentacao.builder()
                        .id(1L)
                        .descricao("Fifa23")
                        .data(LocalDate.now())
                        .qunatidade(2)
                        .valor(new BigDecimal(100))
                        .tipo("SAIDA")
                        .modelo(modeloRepository.findById(2L).get())
                        .build())
);

}

}
