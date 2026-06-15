package com.controld.controld.internal.game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;


public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByName(String name);
    List<Game> findByPublisherId(Long publisherId);
    List<Game> findByPlatformsId(Long platformId);
    List<Game> findByGenresId(Long platformId);

    @Transactional
    void deleteByPublisherId(Long publisherId);
}
