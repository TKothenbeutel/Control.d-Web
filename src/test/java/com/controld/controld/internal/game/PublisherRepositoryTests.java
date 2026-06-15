package com.controld.controld.internal.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class PublisherRepositoryTests {
    
    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void shouldFindPublisherById(){
        Optional<Publisher> publisher = publisherRepository.findById(1L);

        assertThat(publisher).isPresent();
        assertThat(publisher.get().getName()).isEqualTo("Valve");
        
    }

    @Test
    void shouldFindPublisherByName(){
        List<Publisher> publishers = publisherRepository.findByName("Valve");
        assertThat(publishers.size()).isEqualTo(1);

        Publisher publisher = publishers.get(0);
        assertThat(publisher.getId()).isEqualTo(1L); 
        assertThat(publisher.getName()).isEqualTo("Valve");
    }

    @Test
    @DirtiesContext
    void shouldSaveNewlyMadePublisher(){
        Publisher publisher = new Publisher("Sony");
        
        publisher = publisherRepository.save(publisher);

        Optional<Publisher> savedPublisher = publisherRepository.findById(publisher.getId());
        assertThat(savedPublisher).isPresent();
        assertThat(savedPublisher.get()).isEqualTo(publisher);
    }

    @Test
    @DirtiesContext
    void shouldDeletePublisher(){
        publisherRepository.deleteById(1L);
        publisherRepository.flush();

        Optional<Publisher> publisher = publisherRepository.findById(1L);
        assertThat(publisher).isEmpty();
    }
}
