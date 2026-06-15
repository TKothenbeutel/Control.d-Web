package com.controld.controld.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controld.controld.internal.game.Game;
import com.controld.controld.internal.game.GameRepository;
import com.controld.controld.internal.game.Genre;
import com.controld.controld.internal.account.ReviewRepository;
import com.controld.controld.internal.game.GenreRepository;
import com.controld.controld.internal.game.Platform;
import com.controld.controld.internal.game.PlatformRepository;
import com.controld.controld.internal.game.Publisher;
import com.controld.controld.internal.game.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final PublisherRepository publisherRepository;
    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;

    private final ReviewRepository reviewRepository;

    public GameService(GameRepository gameRepo, PublisherRepository publisherRepo, PlatformRepository platformRepo, GenreRepository genreRepo, ReviewRepository reviewRepo){
        this.gameRepository = gameRepo;
        this.publisherRepository = publisherRepo;
        this.platformRepository = platformRepo;
        this.genreRepository = genreRepo;
        this.reviewRepository = reviewRepo;
    }


    /*
    * Retrievals of objects
    */

    //Games
    public Game getGame(long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()){
            return game.get();
        }
        return null;
    }
    public List<Game> getGames(){
        return gameRepository.findAll();
    }
    public List<Game> getGamesByName(String name){
        return gameRepository.findByName(name);
    }
    public List<Game> getGamesByPublisher(Long publisherId){
        return gameRepository.findByPublisherId(publisherId);
    }
    public List<Game> getGamesByPlatform(Long platformId){
        return gameRepository.findByPlatformsId(platformId);
    }
    public List<Game> getGamesByGenre(Long genreId){
        return gameRepository.findByGenresId(genreId);
    }

    //Publisher
    public Publisher getPublisher(Long id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if(publisher.isPresent()){
            return publisher.get();
        }
        return null;
    }
    public List<Publisher> getPublishers(){
        return publisherRepository.findAll();
    }
    public List<Publisher> getReviewsByName(String name){
        return publisherRepository.findByName(name);
    }

    //Platform
    public Platform getPlatform(Long id){
        Optional<Platform> platform = platformRepository.findById(id);
        if(platform.isPresent()){
            return platform.get();
        }
        return null;
    }
    public List<Platform> getPlatforms(){
        return platformRepository.findAll();
    }
    public List<Platform> getPlatformsByName(String name){
        return platformRepository.findByName(name);
    }

    //Genre
    public Genre getGenre(Long id){
        Optional<Genre> genre = genreRepository.findById(id);
        if(genre.isPresent()){
            return genre.get();
        }
        return null;
    }
    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }
    public List<Genre> getGenresByName(String name){
        return genreRepository.findByName(name);
    }
    

    /*
    * Additions
    */

    @Transactional
    public Game addGame(Game game, Long publisherId){
        Game nGame = new Game(game.getName(), game.getDescription(), game.getReleaseDate(), 0, 0);
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(publisher.isPresent()){
            nGame.setPublisher(publisher.get());
            return gameRepository.save(nGame);
        }
        return null;
    }

    @Transactional
    public Publisher addPublisher(Publisher publisher){
        Publisher nPublisher = new Publisher(publisher.getName());
        return publisherRepository.save(nPublisher);
    }

    @Transactional
    public Platform addPlatform(Platform platform){
        Platform nPlatform = new Platform(platform.getName());
        return platformRepository.save(nPlatform);
    }

    @Transactional
    public Genre addGenre(Genre genre){
        Genre nGenre = new Genre(genre.getName());
        return genreRepository.save(nGenre);
    }



    /*
    * Deletions
    */

    @Transactional
    public void deleteGame(Long id){
        //Delete reviews first
        reviewRepository.deleteByGameId(id);
        //Delete game
        gameRepository.deleteById(id);
    }

    @Transactional
    public void deletePublisher(Long id){
        //Delete games first
        gameRepository.deleteByPublisherId(id);
        //Delete publisher
        publisherRepository.deleteById(id);
    }

    @Transactional
    public void deletePlatform(Long id){
        //Remove from games
        for(Game game : gameRepository.findByPlatformsId(id)){
            game.removePlatform(id);
            gameRepository.save(game);
        }
        //Delete platform
        platformRepository.deleteById(id);
    }

    @Transactional
    public void deleteGenre(Long id){
        //Remove from games
        for(Game game : gameRepository.findByGenresId(id)){
            game.removePlatform(id);
            gameRepository.save(game);
        }
        //Delete platform
        platformRepository.deleteById(id);
    }

}
