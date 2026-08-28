package com.scryng.music.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.scryng.music.dto.MusicFilter;
import com.scryng.music.entities.Music;

import jakarta.persistence.criteria.Predicate;

public final class MusicSpecifications {

    private MusicSpecifications() {
    }

    public static Specification<Music> withFilter(MusicFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.codigo() != null && !filter.codigo().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("code")), filter.codigo().toLowerCase()));
            }
            if (filter.titulo() != null && !filter.titulo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")),
                        "%" + filter.titulo().toLowerCase() + "%"));
            }
            if (filter.estilo() != null && !filter.estilo().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("style")), filter.estilo().toLowerCase()));
            }
            if (filter.artista() != null && !filter.artista().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("artist")),
                        "%" + filter.artista().toLowerCase() + "%"));
            }
            if (filter.album() != null && !filter.album().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("album")),
                        "%" + filter.album().toLowerCase() + "%"));
            }
            if (filter.duracao() != null) {
                predicates.add(cb.equal(root.get("duration"), filter.duracao()));
            }
            if (filter.duracaoMenor() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("duration"), filter.duracaoMenor()));
            }
            if (filter.duracaoMaior() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("duration"), filter.duracaoMaior()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
