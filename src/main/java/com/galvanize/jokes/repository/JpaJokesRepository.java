package com.galvanize.jokes.repository;

import com.galvanize.jokes.entity.Jokes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJokesRepository extends JpaRepository<Jokes, Long> {
}
