package com.controld.controld.internal.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldFindAccountById(){
        Optional<Account> account = accountRepository.findById(1L);

        assertThat(account).isPresent();
        assertThat(account.get().getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.get().getUsername()).isEqualTo("foobar");
        
    }

    @Test
    void shouldFindAccountByUsername(){
        List<Account> accounts = accountRepository.findByUsername("foobar");
        assertThat(accounts.size()).isEqualTo(1);

        Account account = accounts.get(0);
        assertThat(account.getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.getId()).isEqualTo(1L);    
    }

    @Test
    void shouldFindAccountByEmail(){
        Optional<Account> account = accountRepository.findByEmail("fake@mail.com");

        assertThat(account).isPresent();
        assertThat(account.get().getId()).isEqualTo(1L); 
        assertThat(account.get().getUsername()).isEqualTo("foobar");
    }

    @Test
    void shouldFindAccountByFavoriteGameId(){
        List<Account> accounts = accountRepository.findAccountsByFavoriteGamesId(1L);
        assertThat(accounts.size()).isEqualTo(1);

        Account account = accounts.get(0);
        assertThat(account.getEmail()).isEqualTo("fake@mail.com");
        assertThat(account.getId()).isEqualTo(1L);
    }

    @Test
    @DirtiesContext
    void shouldSaveNewlyMadeAccount(){
        Account account = new Account("email@email.com", "JohnDoe", "123");
        accountRepository.save(account);

        Optional<Account> savedAccount = accountRepository.findByEmail(account.getEmail());
        assertThat(savedAccount).isPresent();
        assertThat(savedAccount.get().getId()).isNotNull(); 
        assertThat(savedAccount.get().getUsername()).isEqualTo("JohnDoe");
    }

    @Test
    @DirtiesContext
    void shouldDeleteAccount(){
        accountRepository.deleteById(1L);
        accountRepository.flush();

        Optional<Account> account = accountRepository.findById(1L);
        assertThat(account).isEmpty();
    }
}
