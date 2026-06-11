package com.controld.controld.internal.account;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@DataJpaTest
public class AccountRepositoryTests {

    Logger logger = LoggerFactory.getLogger(AccountRepositoryTests.class);

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
        assertThat(accounts.get(0).getEmail()).isEqualTo("fake@mail.com");
        assertThat(accounts.get(0).getId()).isEqualTo(1);
        
    }

    @Test
    void shouldFindAccountByUsernameFail(){
        List<Account> accounts = accountRepository.findByUsername("Nonexistent");

        assertThat(accounts).isNotNull();
        assertThat(accounts).isEmpty();
    }
}
