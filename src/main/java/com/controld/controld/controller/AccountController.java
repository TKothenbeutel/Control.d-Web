package com.controld.controld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controld.controld.internal.account.Account;
import com.controld.controld.service.AccountService;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Account>> findAll() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
    

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        if(account != null){
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody Account account, UriComponentsBuilder ucb) {
        Account savedAccount = accountService.addAccount(account);
        URI newAccountLocation = ucb
            .path("/accounts/{id}")
            .buildAndExpand(savedAccount.getId())
            .toUri();
        return ResponseEntity.created(newAccountLocation).build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
    
    
}
