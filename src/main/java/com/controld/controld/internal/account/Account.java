package com.controld.controld.internal.account;

import java.util.HashSet;
import java.util.Set;

import com.controld.controld.internal.game.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(
        name = "account_games",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> favoriteGames = new HashSet<>();
    
    protected Account(){
    }

    public Account(String email, String username){
        this.email = email;
        this.username = username;
    }

    //Getter and setters
    public long getId(){
        return accountId;
    }

    void setAccountId(long id){
        this.accountId = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    void addGame(Game game){
        favoriteGames.add(game);
    }

    void removeGame(Long gameId){
        for(Game game : favoriteGames){
            if(game.getId() == gameId){
                favoriteGames.remove(game);
                return;
            }
        }
    }
}