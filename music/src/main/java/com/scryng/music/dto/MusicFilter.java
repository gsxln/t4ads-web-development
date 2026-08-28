package com.scryng.music.dto;

public record MusicFilter(
        String codigo,
        String titulo,
        String estilo,
        String artista,
        String album,
        Double duracao,
        Double duracaoMenor,
        Double duracaoMaior) {

    public boolean hasAnyFilter() {
        return codigo != null || titulo != null || estilo != null || artista != null
                || album != null || duracao != null || duracaoMenor != null || duracaoMaior != null;
    }
}
