package com.controld.controld.internal.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
public class PlatformRepositoryTests {
    
    @Autowired
    private PlatformRepository platformRepository;

    @Test
    void shouldFindPlatformById(){
        Optional<Platform> platform = platformRepository.findById(1);

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
}
