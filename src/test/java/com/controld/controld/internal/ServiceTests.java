package com.controld.controld.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import com.controld.controld.internal.account.*;
import com.controld.controld.internal.game.*;
import com.controld.controld.service.AccountService;
import com.controld.controld.service.GameService;

@DataJpaTest
@ComponentScan("com.controld.controld.service")
public class ServiceTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameService gameService;

    /*
    * Creation with foreign objects
    */

    @Test
    @DirtiesContext
    void shouldCreateANewGame(){
        Game game = new Game("Game", "This is a game", "2000-1-1", 0, 0);

        game = gameService.addGame(game, 1L);

        Game foundGame = gameService.getGame(game.getId());
        assertThat(foundGame).isNotNull();
        assertThat(foundGame).isEqualTo(game);
    }


    /*
    * Deletion cascading
    */

    @Test
    @DirtiesContext
    void shouldDeleteAccountAndReviews(){
        List<Review> reviews = accountService.getReviewsByAccountId(1L);
        assertThat(reviews).isNotEmpty();

        accountService.deleteAccount(1L);

        Account account = accountService.getAccount(1L);
        assertThat(account).isNull();

        reviews = accountService.getReviewsByAccountId(1L);
        assertThat(reviews).isEmpty();
    }

    @Test
    @DirtiesContext
    void shouldDeleteGameAndReviews(){
        List<Review> reviews = accountService.getReviewsByGameId(1L);
        assertThat(reviews).isNotEmpty();

        gameService.deleteGame(1L);

        Game game = gameService.getGame(1L);
        assertThat(game).isNull();

        reviews = accountService.getReviewsByGameId(1L);
        assertThat(reviews).isEmpty();
    }

    @Test
    @DirtiesContext
    void shouldDeletePublisherAndGames(){
        List<Game> games = gameService.getGamesByPublisher(1L);
        assertThat(games).isNotEmpty();

        gameService.deletePublisher(1L);

        Publisher publisher = gameService.getPublisher(1L);
        assertThat(publisher).isNull();

        games = gameService.getGamesByPublisher(1L);
        assertThat(games).isEmpty();
    }
}
