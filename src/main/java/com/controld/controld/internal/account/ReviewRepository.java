package com.controld.controld.internal.account;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByAccountId(Long accountId);
    public List<Review> findByGameId(Long gameId);

    @Transactional
    void deleteByAccountId(Long accountId);
    @Transactional
    void deleteByGameId(Long gameId);
}
