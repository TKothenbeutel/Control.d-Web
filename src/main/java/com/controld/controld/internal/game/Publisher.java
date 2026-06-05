package com.controld.controld.internal.game;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "id")
    private long publisherID;

    private String name;

    @ManyToMany(mappedBy = "publisher")
    private Set<Game> games = new HashSet<>();

    public Publisher(){
    }

    public Publisher(String name){
        this.name = name;
    }

    //Getters + Setters
    public long getID(){
        return publisherID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
