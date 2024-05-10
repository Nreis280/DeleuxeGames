package br.com.fiap.deluxegames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.deluxegames.model.Movimentacao;
import br.com.fiap.deluxegames.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    record TotalPorModelo (String modelo, BigDecimal valor){}

    @Autowired
    MovimentacaoRepository repository;

    @GetMapping
    public Page<Movimentacao> index(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer mes,
            @ParameterObject @PageableDefault(sort = "data", direction = Direction.DESC) Pageable pageable

    ) {

        if (mes != null && modelo != null) {
            return repository.findByModeloNomeAndMes(modelo, mes, pageable);
        }

        if (mes != null) {
            return repository.findByMes(mes, pageable);
        }

        if (modelo != null) {
            return repository.findByModeloNomeIgnoreCase(modelo, pageable);
        }

        return repository.findAll(pageable);
    }

    @GetMapping("maior")
    public Movimentacao getMaior(
            @PageableDefault(size = 1, sort = "valor", direction = Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("ultima")
    public Movimentacao getUltima() {
        var pageable = PageRequest.of(0, 1, Direction.DESC, "data");
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("menor")
    public Movimentacao getMenor() {
        return repository.findFirstByOrderByValor();
    }

    @GetMapping("total-por-modelo")
    public List<TotalPorModelo> getTotalPorModelo() {
        var movimentacoes = repository.findAll();

        Map<String, BigDecimal> collect = movimentacoes.stream()
                .collect(
                        Collectors.groupingBy(
                                m -> m.getModelo().getNome(),
                                Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValor, BigDecimal::add)));

        return collect
                .entrySet()
                .stream()
                .map(e -> new TotalPorModelo(e.getKey(), e.getValue()))
                .toList();

    }

    @PostMapping
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao) {
        return repository.save(movimentacao);
    }

}
