package br.com.deluxegames.DeluxeGames.model;

import java.util.Random;

public record Modelo(Long id, String nome, String icone) {
    public Modelo (Long id, String nome, String icone) {
        var key = (id != null)? id : new Random().nextLong();
        this.id = key;
        this.nome = nome;
        this.icone = icone;
    }
}
