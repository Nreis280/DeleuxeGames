package br.com.deluxegames.DeluxeGames.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.deluxegames.DeluxeGames.model.Modelo;
import br.com.deluxegames.DeluxeGames.repository.ModeloRepository;

@RestController
@RequestMapping("modelo")
public class ModeloController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ModeloRepository modeloRepository;

    @GetMapping
    public List<Modelo> index() {
        return modeloRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Modelo create(@RequestBody Modelo modelo) {
        log.info("cadastrando modelo: {}", modelo);
        modeloRepository.save(modelo);
        return modelo;
    }

    @GetMapping("{id}")
    public ResponseEntity<Modelo> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        return modeloRepository
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando modelo {}", id);

        verificarSeExisteModelo(id);

        modeloRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Modelo modelo){
        log.info("atualizando modelo id {} para {}", id, modelo);
        
        verificarSeExisteModelo(id);

        modelo.setId(id);
        modeloRepository.save(modelo);
        return ResponseEntity.ok(modelo);
    }
    
    private void verificarSeExisteModelo(Long id) {
        modeloRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "modelo não encontrado" )
            );
    }
    
}
