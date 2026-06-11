package com.controld.controld.internal.game;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface GameRepository extends Repository<Game, Long> {
    public Game findById(long id);
    public List<Game> findByName(String name);
    public List<Game> findGamesByPublisherId(Long publisherId);
    public List<Game> findGamesByPlatformsId(Long platformId);
    public List<Game> findGamesByGenresId(Long platformId);
}
