package com.controld.controld.internal.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest
public class GameRepositoryTests {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void shouldFindGameById(){
        Game game = gameRepository.findById(1);

        assertThat(game).isNotNull();
        assertThat(game.getName()).isEqualTo("Portal");
        assertThat(game.getReleaseDate().toString()).isEqualTo("2007-10-10");
        
    }

    @Test
    void shouldFindGameByName(){
        List<Game> games = gameRepository.findByName("Portal");
        assertThat(games).isNotNull();
        assertThat(games.size()).isEqualTo(1);

        Game game = games.get(0);
        assertThat(game.getId()).isEqualTo(1L); 
        assertThat(game.getName()).isEqualTo("Portal");
        assertThat(game.getReleaseDate().toString()).isEqualTo("2007-10-10");   
    }

    @Test
    void shouldFindGameByPublisherId(){
        List<Game> games = gameRepository.findGamesByPublisherId(1L);
        assertThat(games).isNotNull();
        assertThat(games.size()).isEqualTo(1);

        Game game = games.get(0);
        assertThat(game.getId()).isEqualTo(1L); 
        assertThat(game.getName()).isEqualTo("Portal");
        assertThat(game.getReleaseDate().toString()).isEqualTo("2007-10-10");  
    }

    @Test
    void shouldFindGameByPlatformId(){
        List<Game> games = gameRepository.findGamesByPlatformsId(1L);
        assertThat(games).isNotNull();
        assertThat(games.size()).isEqualTo(1);

        Game game = games.get(0);
        assertThat(game.getId()).isEqualTo(1L); 
        assertThat(game.getName()).isEqualTo("Portal");
        assertThat(game.getReleaseDate().toString()).isEqualTo("2007-10-10");  
    }

    @Test
    void shouldFindGameByGenreId(){
        List<Game> games = gameRepository.findGamesByGenresId(1L);
        assertThat(games).isNotNull();
        assertThat(games.size()).isEqualTo(1);

        Game game = games.get(0);
        assertThat(game.getId()).isEqualTo(1L); 
        assertThat(game.getName()).isEqualTo("Portal");
        assertThat(game.getReleaseDate().toString()).isEqualTo("2007-10-10");  
    }

}
