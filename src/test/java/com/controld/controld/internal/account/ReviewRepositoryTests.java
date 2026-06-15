package com.controld.controld.internal.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.controld.controld.internal.game.Game;
import com.controld.controld.internal.game.GameRepository;


@DataJpaTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    //Used in tests for creating/deleting reviews
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private GameRepository gameRepository;

    @Test
    void shouldGetReviewsByAccountId(){
        List<Review> reviews = reviewRepository.findByAccountId(1L);
        assertThat(reviews.size()).isEqualTo(1);

        Review review = reviews.get(0);
        assertThat(review.getId()).isEqualTo(1);
        assertThat(review.getRating()).isEqualTo(4.9f);
        assertThat(review.getTitle()).isEqualTo("Very good game!");
    }

    @Test
    void shouldGetReviewsByGameId(){
        List<Review> reviews = reviewRepository.findByGameId(1L);
        assertThat(reviews.size()).isEqualTo(1);

        Review review = reviews.get(0);
        assertThat(review.getId()).isEqualTo(1);
        assertThat(review.getRating()).isEqualTo(4.9f);
        assertThat(review.getTitle()).isEqualTo("Very good game!");
    }

    @Test
    void shouldGetReviewByAccountIdAndGameId(){
        Optional<Review> review = reviewRepository.findByAccountIdAndGameId(1L, 1L);

        assertThat(review).isPresent();
        assertThat(review.get().getId()).isEqualTo(1);
        assertThat(review.get().getRating()).isEqualTo(4.9f);
        assertThat(review.get().getTitle()).isEqualTo("Very good game!");
    }

    @Test
    @DirtiesContext
    void shouldCreateANewReview(){
        Review review = new Review(1.3f, "A game", "That's a game alright");

        Account account = accountRepository.findById(2L).get();
        Game game = gameRepository.findById(1L).get();

        review.setAccount(account);
        review.setGame(game);

        review = reviewRepository.saveAndFlush(review);

        Optional<Review> foundReview = reviewRepository.findByAccountIdAndGameId(account.getId(), game.getId());
        assertThat(foundReview).isPresent();
        assertThat(foundReview.get()).isEqualTo(review);
    }

    @Test
    @DirtiesContext
    void shouldDeleteReview(){
        Optional<Review> review = reviewRepository.findById(1L);
        assertThat(review).isPresent();

        reviewRepository.deleteById(1L);

        review = reviewRepository.findById(1L);
        assertThat(review).isEmpty();
    }
}
