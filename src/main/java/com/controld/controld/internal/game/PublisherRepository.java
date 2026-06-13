package com.controld.controld.internal.game;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findById(long id);
    List<Publisher> findByName(String name);
}
