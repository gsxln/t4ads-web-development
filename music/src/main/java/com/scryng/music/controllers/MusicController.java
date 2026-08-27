package com.scryng.music.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scryng.music.dto.MusicFilter;
import com.scryng.music.entities.Music;
import com.scryng.music.services.MusicService;

@RestController
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService service;

    @GetMapping
    public ResponseEntity<List<Music>> getAll(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estilo,
            @RequestParam(required = false) String artista,
            @RequestParam(required = false) String album,
            @RequestParam(required = false) Double duracao,
            @RequestParam(required = false) Double duracaoMenor,
            @RequestParam(required = false) Double duracaoMaior) {
        MusicFilter filter = new MusicFilter(
                codigo, titulo, estilo, artista, album, duracao, duracaoMenor, duracaoMaior);
        return ResponseEntity.ok(service.findByFilter(filter));
    }

    @GetMapping("{id}")
    public ResponseEntity<Music> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Music> save(@RequestBody Music music) {
        Music m = service.save(music);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(m.getId())
                .toUri();

        return ResponseEntity.created(location).body(m);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
            @RequestBody Music music) {
        service.update(music, id);
        return ResponseEntity.noContent().build();
    }
}
