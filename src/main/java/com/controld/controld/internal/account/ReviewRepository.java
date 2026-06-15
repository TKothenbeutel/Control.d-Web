package com.controld.controld.internal.account;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByAccountIdAndGameId(Long accountId, Long gameId);
    List<Review> findByAccountId(Long accountId);
    List<Review> findByGameId(Long gameId);

    @Transactional
    void deleteByAccountId(Long accountId);
    @Transactional
    void deleteByGameId(Long gameId);
}
