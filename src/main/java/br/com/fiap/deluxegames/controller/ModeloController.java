package br.com.fiap.deluxegames.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.fiap.deluxegames.model.Modelo;
import br.com.fiap.deluxegames.repository.ModeloRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("modelo")
@Slf4j
@CacheConfig(cacheNames = "modelos")
@Tag(name = "modelos", description = "Endpoint relacionados aos modelos de Video games")
public class ModeloController {

    @Autowired
    ModeloRepository modeloRepository;

    @GetMapping
    @Cacheable
    @Operation(summary = "Lista todos os modelos cadastrados no sistema.", description = "Endpoint que retorna um array de objetos do tipo modelos com todos os modelos do usuário atual")
    public List<Modelo> index() {
        return modeloRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação do Modelo"),
            @ApiResponse(responseCode = "201", description = "Modelo cadastrado com sucesso")
    })
    public Modelo create(@RequestBody @Valid Modelo modelo) {
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
    @CacheEvict(allEntries = true)
    public void destroy(@PathVariable Long id) {
        log.info("apagando modelo {}", id);

        verificarSeExisteModelo(id);
        modeloRepository.deleteById(id);
    }


    @PutMapping("{id}")
    public Modelo update(@PathVariable Long id, @RequestBody Modelo modelo){
        log.info("atualizando modelo id {} para {}", id, modelo);
        
        verificarSeExisteModelo(id);

        modelo.setId(id);
        return modeloRepository.save(modelo);

    }

    
    private void verificarSeExisteModelo(Long id) {
        modeloRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "modelo não encontrado" )
            );
    }


}
