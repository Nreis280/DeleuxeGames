package br.com.deluxegames.DeluxeGames.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.deluxegames.DeluxeGames.model.Modelo;





@RestController
@RequestMapping("modelo")
public class ModeloController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    List<Modelo> repository = new ArrayList<>();

    private Optional<Modelo> buscarModeloPorId(Long id) {
        var optionalModelo = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return optionalModelo;
    }


    @GetMapping
    public List<Modelo> index() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<Modelo> create(@RequestBody Modelo modelo){
        log.info("Cadastrando modelo: {}", modelo);
        repository.add(modelo);
        return ResponseEntity.status(HttpStatus.CREATED).body((modelo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getById(@PathVariable Long id){
        var opitionalModelo = repository.stream().filter((c) -> c.id().equals(id)).findFirst();

        if (opitionalModelo.isEmpty())
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(opitionalModelo.get());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("Apagando categoria {}", id);

        var opitionalModelo = buscarModeloPorId(id);

        if (opitionalModelo.isEmpty())
            return ResponseEntity.notFound().build();

        repository.remove(opitionalModelo.get());

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Modelo modelo){
        log.info("Atualizando modelo id {} para {}", id, modelo);
        
        var optionalmodelo = buscarModeloPorId(id);

        if (optionalmodelo.isEmpty())
            return ResponseEntity.notFound().build();

        var modeloEncontrado = optionalmodelo.get();
        var modeloAtualizado = new Modelo(id, modelo.nome(), modelo.icone());
        repository.remove(modeloEncontrado);
        repository.add(modeloAtualizado);

        return ResponseEntity.ok().body(modeloAtualizado);
    }
    
}
