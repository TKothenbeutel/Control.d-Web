package com.controld.controld.internal.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldFindAccountById(){
        Account account = accountRepository.findById(1);

        assertThat(account).isNotNull();
        assertThat(account.getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.getUsername()).isEqualTo("foobar");
        
    }

    @Test
    void shouldFindAccountByUsername(){
        List<Account> accounts = accountRepository.findByUsername("foobar");
        assertThat(accounts).isNotNull();
        assertThat(accounts.size()).isEqualTo(1);

        Account account = accounts.get(0);
        assertThat(account.getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.getId()).isEqualTo(1L);    
    }

    @Test
    void shouldFindAccountByEmail(){
        Account account = accountRepository.findByEmail("fake@mail.com");

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(1L); 
        assertThat(account.getUsername()).isEqualTo("foobar");
    }

    @Test
    void shouldFindAccountByFavoriteGameId(){
        List<Account> accounts = accountRepository.findAccountsByFavoriteGamesId(1L);
        assertThat(accounts).isNotNull();
        assertThat(accounts.size()).isEqualTo(1);

        Account account = accounts.get(0);
        assertThat(account.getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.getId()).isEqualTo(1L);
    }
}
