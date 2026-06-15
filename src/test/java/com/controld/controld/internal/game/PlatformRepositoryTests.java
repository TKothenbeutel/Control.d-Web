package com.controld.controld.internal.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class PlatformRepositoryTests {
    
    @Autowired
    private PlatformRepository platformRepository;

    @Test
    void shouldFindPlatformById(){
        Optional<Platform> platform = platformRepository.findById(1L);

        assertThat(platform).isPresent();
        assertThat(platform.get().getName()).isEqualTo("PC");
        
    }

    @Test
    void shouldFindPlatformByName(){
        List<Platform> platforms = platformRepository.findByName("Nintendo Switch");
        assertThat(platforms.size()).isEqualTo(1);

        Platform platform = platforms.get(0);
        assertThat(platform.getId()).isEqualTo(2L); 
        assertThat(platform.getName()).isEqualTo("Nintendo Switch");
    }

    @Test
    @DirtiesContext
    void shouldSaveNewlyMadePlatform(){
        Platform platform = new Platform("Potato");
        
        platform = platformRepository.save(platform);

        Optional<Platform> savedPlatform = platformRepository.findById(platform.getId());
        assertThat(savedPlatform).isPresent();
        assertThat(savedPlatform.get()).isEqualTo(platform);
    }

    @Test
    @DirtiesContext
    void shouldDeletePlatform(){
        platformRepository.deleteById(1L);
        platformRepository.flush();

        Optional<Platform> platform = platformRepository.findById(1L);
        assertThat(platform).isEmpty();
    }
}
