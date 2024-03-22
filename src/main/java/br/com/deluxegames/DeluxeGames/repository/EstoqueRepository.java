package br.com.deluxegames.DeluxeGames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.deluxegames.DeluxeGames.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
    
}
