package com.controld.controld.controller;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.ExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.controld.controld.internal.account.Account;

@AutoConfigureRestTestClient
@SpringBootTest
public class AccountControllerTests {

    @Autowired
    RestTestClient restClient;

    @Test
    void shouldReturnAccountById(){
        restClient
            .get()
            .uri("/accounts/1")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("id").isEqualTo("1")
            .jsonPath("email").isEqualTo("fake@mail.com")
            .jsonPath("username").isEqualTo("foobar");
    }

    @Test
    void shouldNotReturnAccountByUnknownId(){
        restClient
            .get()
            .uri("/accounts/9999")
            .exchange()
            .expectStatus().isNotFound()
            .expectBody().isEmpty();
    }

    @Test
    void shouldReturnAllAccounts(){
        restClient
            .get()
            .uri("/accounts")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$..id").isEqualTo(List.of(1,2))
            .jsonPath("$..email").isEqualTo(List.of("fake@mail.com","test@mail.com"))
            .jsonPath("$..username").isEqualTo(List.of("foobar","Jane_Doe"));
    }

    @Test
    @DirtiesContext
    void shouldCreateANewAccount(){
        Account account = new Account("email2@email.com", "JohnDoe", "123");
        account.setId(99L);
        ExchangeResult response = restClient
            .post()
            .uri("/accounts")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
            .body(account)
            .exchange()
            .expectStatus().isCreated()
            .returnResult();

        URI accountLocation = response.getResponseHeaders().getLocation();
        restClient
            .get()
            .uri(accountLocation)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("id").isNotEmpty()
            .jsonPath("email").isEqualTo("email2@email.com")
            .jsonPath("username").isEqualTo("JohnDoe");
    }

    @Test
    @DirtiesContext
    void shouldDeleteAccountById(){
        restClient
            .delete()
            .uri("/accounts/1")
            .exchange()
            .expectStatus().isNoContent();

        restClient
            .get()
            .uri("/accounts/1")
            .exchange()
            .expectStatus().isNotFound()
            .expectBody().isEmpty();
    }

}
