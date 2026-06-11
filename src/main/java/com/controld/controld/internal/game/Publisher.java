package com.controld.controld.internal.game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "id")
    private long publisherId;

    @Column(nullable = false)
    private String name;

    public Publisher(){
    }

    public Publisher(String name){
        this.name = name;
    }

    //Getters + Setters
    public long getId(){
        return publisherId;
    }

    void setId(Long id){
        this.publisherId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
