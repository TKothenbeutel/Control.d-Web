package com.controld.controld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.controld.controld.internal.account.Account;
import com.controld.controld.internal.account.AccountRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    private AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Account>> findAll() {
        return ResponseEntity.ok(accountRepository.findAll());
    }
    

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        System.out.println(account);
        if(account.isPresent()){
            return ResponseEntity.ok(account.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody Account account, UriComponentsBuilder ucb) {
        Account newAccount = new Account(account.getEmail(), account.getUsername(), account.getPassword());
        Account savedAccount = accountRepository.save(newAccount);
        URI newAccountLocation = ucb
            .path("/accounts/{id}")
            .buildAndExpand(savedAccount.getId())
            .toUri();
        return ResponseEntity.created(newAccountLocation).build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            accountRepository.delete(account.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    
}
