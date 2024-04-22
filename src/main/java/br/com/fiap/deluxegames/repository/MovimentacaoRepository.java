package br.com.fiap.deluxegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.deluxegames.model.Movimentacao;

public interface MovimentacaoRepository 
            extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByModeloNome(String modelo);
    
}
