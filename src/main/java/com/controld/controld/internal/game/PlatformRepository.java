package com.controld.controld.internal.game;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findById(long id);
    List<Platform> findByName(String name);
}
