package com.controld.controld.internal.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    List<Platform> findByName(String name);
}
