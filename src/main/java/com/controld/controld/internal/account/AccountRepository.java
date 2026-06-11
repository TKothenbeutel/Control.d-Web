package com.controld.controld.internal.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    public Account findById(long id);
    public Account findByEmail(String email);
    public List<Account> findByUsername(String username);
    public List<Account> findAccountsByFavoriteGamesId(Long gameId);
}
