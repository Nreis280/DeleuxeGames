package br.com.fiap.deluxegames.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.deluxegames.model.Modelo;
import br.com.fiap.deluxegames.model.Movimentacao;
import br.com.fiap.deluxegames.repository.ModeloRepository;
import br.com.fiap.deluxegames.repository.MovimentacaoRepository;

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
                                                Modelo.builder().id(1L).nome("xbox").icone("joystick").build(),
                                                Modelo.builder().id(2L).nome("computador").icone("swords").build(),
                                                Modelo.builder().id(3L).nome("nintendo").icone("gamepad").build()));

                movimentacaoRepository.saveAll(
                                List.of(
                                                Movimentacao.builder()
                                                                .id(1L)
                                                                .descricao("Luz")
                                                                .data(LocalDate.now())
                                                                .quantidade(2)
                                                                .valor(new BigDecimal(100))
                                                                .tipo("SAIDA")
                                                                .modelo(modeloRepository.findById(2L).get())
                                                                .build()));

        }

}
