package com.controld.controld.internal.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


@DataJpaTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

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
        assertThat(reviews).isNotNull();
        assertThat(reviews.size()).isEqualTo(1);

        Review review = reviews.get(0);
        assertThat(review.getId()).isEqualTo(1);
        assertThat(review.getRating()).isEqualTo(4.9f);
        assertThat(review.getTitle()).isEqualTo("Very good game!");
    }
}
