package br.com.deluxegames.DeluxeGames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.deluxegames.DeluxeGames.model.Estoque;
import br.com.deluxegames.DeluxeGames.repository.EstoqueRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("movimentacao")
public class EstoqueController {

    @Autowired
    EstoqueRepository repository;

    @PostMapping
    public Estoque create(@RequestBody @Valid Estoque movimentacao){
        return repository.save(movimentacao);
    }
    
}