package com.controld.controld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controld.controld.internal.account.Account;
import com.controld.controld.internal.account.AccountRepository;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    private AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{accountId}")
    private ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        System.out.println(account);
        if(account.isPresent()){
            return ResponseEntity.ok(account.get());
        }
        return ResponseEntity.notFound().build();
    }
    
}
