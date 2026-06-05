package com.controld.controld.internal.account;

import java.util.HashSet;
import java.util.Set;

import com.controld.controld.internal.game.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    private Long accountID;

    private String email;
    private String username;

    @OneToMany(mappedBy = "account")
    //Maybe this should be:
    //public Set<Review> getReviews() { return reviews; }
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany
    @JoinTable(
        name = "account_games",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> favoriteGames = new HashSet<Game>();

    public Account(){
    }

    public Account(String email, String username){
        this.email = email;
        this.username = username;
    }

    //Getter and setters
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
}
