package com.controld.controld.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

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

}
