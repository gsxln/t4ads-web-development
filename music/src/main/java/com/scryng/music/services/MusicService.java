package com.scryng.music.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scryng.music.entities.Music;
import com.scryng.music.repositories.MusicRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MusicService {

    @Autowired
    private MusicRepository repository;

    public List<Music> findAll() {
        return repository.findAll();
    }

    public Music findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não cadastrada"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Música não cadastrada");
    }

    public Music save(Music music) {
        return repository.save(music);
    }

    public void update(Music music, Long id) {
        Music m = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não cadastrada"));

        m.setCode(music.getCode());
        m.setTitle(music.getTitle());
        m.setStyle(music.getStyle());
        m.setArtist(music.getArtist());
        m.setAlbum(music.getAlbum());
        m.setDuration(music.getDuration());

        repository.save(m);
    }
}
