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
                                                Modelo.builder().id(3L).nome("Playstation").icone("gamepad").build()));

                movimentacaoRepository.saveAll(
                                List.of(
                                        Movimentacao.builder()
                                                        .id(1L)
                                                        .descricao("Fifa-22")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(100.00))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(1L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(2L)
                                                        .descricao("Warzone")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(100.00))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(2L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(3L)
                                                        .descricao("COD WW2")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(150.49))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(3L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(4L)
                                                        .descricao("Forza Horaizon")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(199.90))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(1L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(5L)
                                                        .descricao("Spider-man")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(799.99))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(3L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(6L)
                                                        .descricao("EA24 FC")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(600.00))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(3L).get())
                                                        .build(),     
                                        Movimentacao.builder()
                                                        .id(7L)
                                                        .descricao("NBA24")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(459.90))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(1L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(8L)
                                                        .descricao("GTA-V")
                                                        .data(LocalDate.now())
                                                        .quantidade(10)
                                                        .valor(new BigDecimal(1200.00))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(3L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(9L)
                                                        .descricao("Zelda")
                                                        .data(LocalDate.now())
                                                        .quantidade(4)
                                                        .valor(new BigDecimal(399.99))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(2L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(10L)
                                                        .descricao("Cuphead")
                                                        .data(LocalDate.now())
                                                        .quantidade(2)
                                                        .valor(new BigDecimal(550.89))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(2L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(11L)
                                                        .descricao("Battlefield 2042")
                                                        .data(LocalDate.now())
                                                        .quantidade(1)
                                                        .valor(new BigDecimal(266.00))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(3L).get())
                                                        .build(),
                                        Movimentacao.builder()
                                                        .id(12L)
                                                        .descricao("Sea of Thieves")
                                                        .data(LocalDate.now())
                                                        .quantidade(3)
                                                        .valor(new BigDecimal(239.99))
                                                        .tipo("SAIDA")
                                                        .modelo(modeloRepository.findById(1L).get())
                                                        .build())

                                                        );        

        }

}
