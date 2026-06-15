package com.controld.controld.internal.game;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gameId;

    @Column(nullable = false)
    private String name;
    private String description;
    
    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "rating_total")
    private float ratingTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
        name = "game_platforms",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "game_genres",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();


    public Game(){
    }

    public Game(String name, String description, Date releaseDate, int reviewCount, float ratingTotal){
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.reviewCount = reviewCount;
        this.ratingTotal = ratingTotal;
    }

    public Game(String name, String description, String releaseDate, int reviewCount, float ratingTotal){
        this.name = name;
        this.description = description;
        this.releaseDate = Date.valueOf(releaseDate);;
        this.reviewCount = reviewCount;
        this.ratingTotal = ratingTotal;
    }

    //Getters and setters
    public Long getId(){
        return gameId;
    }

    public void setGameId(Long id){
        this.gameId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
    }

    public int getReviewCount(){
        return reviewCount;
    }

    public float getAvgRating(){
        return ratingTotal / reviewCount;
    }

    public void setRatingTotal(float ratingTotal){
        this.ratingTotal = ratingTotal;
    }

    public Publisher getPublisher(){
        return publisher;
    }

    public void setPublisher(Publisher publisher){
        this.publisher = publisher;
    }

    public void addPlatform(Platform platform){
        platforms.add(platform);
        platform.getGames().add(this);
    }

    public void removePlatform(Long platformId){
        for(Platform platform : platforms){
            if(platform.getId() == platformId){
                platforms.remove(platform);
                platform.getGames().remove(this);
                return;
            }
        }
    }

    public void addGenre(Genre genre){
        genres.add(genre);
    }

    public void removeGenre(Long genreId){
        for(Genre genre : genres){
            if(genre.getId() == genreId){
                genres.remove(genre);
                return;
            }
        }
    }
}
