package com.controld.controld.internal.game;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long genreId;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Game> games = new HashSet<>();

    public Genre(){
    }

    public Genre(String name){
        this.name = name;
    }

    //Getters + Setters
    public long getId(){
        return genreId;
    }

    void setId(Long id){
        this.genreId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Game> getGames(){
        return games;
    }

    public void setGames(Set<Game> games){
        this.games = games;
    }
}
