package br.com.deluxegames.DeluxeGames.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("modelo")
@Slf4j
public class ModeloController {

    @Autowired
    ModeloRepository modeloRepository;

    @GetMapping
    public List<Modelo> index() {
        return modeloRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Modelo create(@RequestBody Modelo modelo) {
        log.info("cadastrando modelo: {}", modelo);
        return modeloRepository.save(modelo);
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
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando modelo {}", id);

        verificarSeExisteModelo(id);
        modeloRepository.deleteById(id);
    }


    @PutMapping("{id}")
    public Modelo update(@PathVariable Long id, @RequestBody Modelo modelo){
        log.info("atualizando Modelo id {} para {}", id, modelo);
        
        verificarSeExisteModelo(id);

        modelo.setId(id);
        return modeloRepository.save(modelo);

    }

    
    private void verificarSeExisteModelo(Long id) {
        modeloRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado" )
            );
    }

    
}
