package com.controld.controld.internal.game;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @Column(name = "id")
    private long genreID;

    private String name;

    @ManyToMany(mappedBy = "fittingGenres")
    private Set<Game> games = new HashSet<>();

    public Genre(){
    }

    public Genre(String name){
        this.name = name;
    }

    //Getters + Setters
    public long getID(){
        return genreID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
