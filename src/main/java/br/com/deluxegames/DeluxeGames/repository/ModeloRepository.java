package br.com.deluxegames.DeluxeGames.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.deluxegames.DeluxeGames.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{
    
}


