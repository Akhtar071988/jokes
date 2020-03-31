package com.galvanize.jokes.service;

import com.galvanize.jokes.entity.Jokes;
import com.galvanize.jokes.repository.JpaJokesRepository;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;

@Service
public class JpaJokesService{
    JpaJokesRepository jpaJokesRepository;

    public JpaJokesService(JpaJokesRepository jpaJokesRepository) {
        this.jpaJokesRepository = jpaJokesRepository;
    }

    public Jokes createJoke(Jokes jokes) {
        return (Jokes) jpaJokesRepository.save(jokes);
    }

    public Jokes findJokeById(long l) {
        return jpaJokesRepository.findById(l).orElse(null);
    }

    public List<Jokes> findAllJokes() {
        return jpaJokesRepository.findAll();
    }

    public Jokes updateJokeById(Jokes expected, Long id) {
        Jokes databaseJokes = findJokeById(id);
        databaseJokes.update(expected);
        return createJoke(databaseJokes);
    }

    public void deleteJokeById(Long id) {
        jpaJokesRepository.deleteById(id);
    }
}
