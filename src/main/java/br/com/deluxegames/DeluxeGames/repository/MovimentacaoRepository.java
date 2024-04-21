package br.com.deluxegames.DeluxeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.deluxegames.DeluxeGames.model.Movimentacao;


public interface MovimentacaoRepository 
            extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByModeloNome(String modelo);
    
}

