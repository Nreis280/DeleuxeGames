package br.com.deluxegames.DeluxeGames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.deluxegames.DeluxeGames.validation.TipoEstoque;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Estoque {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{movimentacao.descricao.notblank}")
    @Size(min = 3, message =  "{movimentacao.descricao.size}")
    private String produto;

    @Positive
    private int quantidade;

    @Positive
    private BigDecimal valor;

    private LocalDate data;

    @TipoEstoque(message = "{movimentacao.tipo.tipomovimentacao}")
    private String tipo; // ENTRADA | SAIDA

    
}