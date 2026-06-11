package com.controld.controld.internal.account;

import com.controld.controld.internal.game.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "id")
    private Long reviewID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private float rating;
    private String title;
    private String body;
    private int likes;

    public Review(){
    }

    public Review(float rating, String title, String body, int likes){
        this.rating = rating;
        this.title = title;
        this.body = body;
        this.likes = likes;
    }

    //Getter and setters
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public float getRating(){
        return rating;
    }

    public void setRating(float rating){
        this.rating = rating;
    }

    public int getLikes(){
        return likes;
    }

    public void setLikes(int likes){
        this.likes = likes;
    }
}
