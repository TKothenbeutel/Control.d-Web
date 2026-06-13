package com.controld.controld.internal.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface GameRepository extends Repository<Game, Long> {
    Optional<Game> findById(long id);
    List<Game> findByName(String name);
    List<Game> findGamesByPublisherId(Long publisherId);
    List<Game> findGamesByPlatformsId(Long platformId);
    List<Game> findGamesByGenresId(Long platformId);
}
