package com.galvanize.jokes.service;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.repository.JpaJokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaJokeService {
    JpaJokeRepository jpaJokeRepository;

    public JpaJokeService(JpaJokeRepository jpaJokeRepository){
        this.jpaJokeRepository = jpaJokeRepository;
    }

    public Joke createJoke(Joke input) {
        return jpaJokeRepository.save(input);
    }

    public List<Joke> getAllJokesByCategory(Category category) {
        return jpaJokeRepository.findAllByCategory(category);
    }

    public Joke update(long jokeId, Joke updateJoke) {
        Joke preUpdateJoke = jpaJokeRepository.findById(jokeId).orElse(null);
        if(preUpdateJoke==null)return null;
        preUpdateJoke.update(updateJoke);
        return preUpdateJoke;
    }

    public boolean deleteById(long jokesId) {
        return jpaJokeRepository.deleteByJokeId(jokesId);
    }

    public List<Joke> getAllJokes() {
        return jpaJokeRepository.findAll();
    }
}
