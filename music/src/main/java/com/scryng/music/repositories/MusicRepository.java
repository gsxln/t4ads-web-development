package com.scryng.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scryng.music.entities.Music;

public interface MusicRepository extends JpaRepository<Music, Long>, JpaSpecificationExecutor<Music> {

}
