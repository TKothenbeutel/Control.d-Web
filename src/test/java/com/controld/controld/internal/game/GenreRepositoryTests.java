package com.controld.controld.internal.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class GenreRepositoryTests {
    
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void shouldFindGenreById(){
        Optional<Genre> genre = genreRepository.findById(1L);

        assertThat(genre).isPresent();
        assertThat(genre.get().getName()).isEqualTo("Puzzle");
        
    }

    @Test
    void shouldFindGenreByName(){
        List<Genre> genres = genreRepository.findByName("Platformer");
        assertThat(genres.size()).isEqualTo(1);

        Genre genre = genres.get(0);
        assertThat(genre.getId()).isEqualTo(2L); 
        assertThat(genre.getName()).isEqualTo("Platformer");
    }

    @Test
    @DirtiesContext
    void shouldSaveNewlyMadeGenre(){
        Genre genre = new Genre("Story-driven");
        
        genre = genreRepository.save(genre);

        Optional<Genre> savedGenre = genreRepository.findById(genre.getId());
        assertThat(savedGenre).isPresent();
        assertThat(savedGenre.get()).isEqualTo(genre);
    }

    @Test
    @DirtiesContext
    void shouldDeleteGenre(){
        genreRepository.deleteById(1L);
        genreRepository.flush();

        Optional<Genre> genre = genreRepository.findById(1L);
        assertThat(genre).isEmpty();
    }
}
