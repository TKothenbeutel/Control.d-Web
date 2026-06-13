package com.controld.controld.internal.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findById(long id);
    Optional<Account> findByEmail(String email);
    List<Account> findByUsername(String username);
    List<Account> findAccountsByFavoriteGamesId(Long gameId);
}
