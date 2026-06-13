package com.controld.controld.internal.game;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findById(long id);
    List<Genre> findByName(String name);
}
