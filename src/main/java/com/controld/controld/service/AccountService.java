package com.controld.controld.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controld.controld.internal.account.Account;
import com.controld.controld.internal.account.AccountRepository;
import com.controld.controld.internal.account.Review;
import com.controld.controld.internal.account.ReviewRepository;
import com.controld.controld.internal.game.Game;
import com.controld.controld.internal.game.GameRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ReviewRepository reviewRepository;

    private final GameRepository gameRepository;


    AccountService(AccountRepository accountRepo, ReviewRepository reviewRepo, GameRepository gameRepo) {
        this.accountRepository = accountRepo;
        this.reviewRepository = reviewRepo;
        this.gameRepository = gameRepo;
    }


    /*
    * Retrievals of objects
    */

    //Accounts
    public Account getAccount(long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }
    public Account getAccountByEmail(String email){
        Optional<Account> account = accountRepository.findByEmail(email);
        if(account.isPresent()){
            return account.get();
        }
        return null;
    }
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
    public List<Account> getAccountsByUsername(String username){
        return accountRepository.findByUsername(username);
    }
    public List<Account> getAccountsByFavoriteGame(Long gameId){
        return accountRepository.findAccountsByFavoriteGamesId(gameId);
    }

    //Reviews
    public Review getReview(Long id){
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            return review.get();
        }
        return null;
    }
    public Review getReviewByIds(Long accountId, Long gameId){
        Optional<Review> review = reviewRepository.findByAccountIdAndGameId(accountId, gameId);
        if(review.isPresent()){
            return review.get();
        }
        return null;
    }
    public List<Review> getReviewsByAccountId(Long id){
        return reviewRepository.findByAccountId(id);
    }
    public List<Review> getReviewsByGameId(Long id){
        return reviewRepository.findByGameId(id);
    }


    /*
    * Additions
    */

    @Transactional
    public Account addAccount(Account account){
        Account nAccount = new Account(account.getEmail(), account.getUsername(), account.getPassword());
        return accountRepository.save(nAccount);
    }

    @Transactional
    public Review addReview(Review review, Long accountId, Long gameId){
        Review nReview = new Review(review.getRating(), review.getTitle(), review.getBody());

        Optional<Account> account = accountRepository.findById(accountId);
        Optional<Game> game = gameRepository.findById(gameId);

        if(account.isPresent() && game.isPresent()){
            nReview.setAccount(account.get());
            nReview.setGame(game.get());
            //TODO: update game rating total + review count
            return reviewRepository.save(nReview);
        }
        return null;
    }


    /*
    * Deletions
    */

    @Transactional
    public void deleteAccount(Long id){
        //Delete reviews first
        reviewRepository.deleteByAccountId(id);
        //Delete account
        accountRepository.deleteById(id);
    }

    @Transactional
    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
        //TODO: update game rating total + review count
    }
}
