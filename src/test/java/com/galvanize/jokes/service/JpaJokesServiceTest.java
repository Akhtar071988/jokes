package com.galvanize.jokes.service;

import com.galvanize.jokes.entity.Jokes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.galvanize.jokes.repository.JpaJokesRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class JpaJokesServiceTest {
    @Autowired
    JpaJokesRepository jpaJokesRepository;

    @Test
    void createJoke() {
        JpaJokesService jpaJokesService = new JpaJokesService(jpaJokesRepository);
        Jokes jokes = new Jokes();
        Jokes actual = jpaJokesService.createJoke(jokes);
        assertEquals(jokes, actual);
    }

    @Test
    void findJokeById() {
          JpaJokesService jpaJokesService = new JpaJokesService(jpaJokesRepository);
          Jokes expected = jpaJokesService.createJoke(new Jokes());
          assertEquals(expected, jpaJokesService.findJokeById(expected.getId()));
    }

    @Test
    void findAllJokes() {
            JpaJokesService jpaJokesService = new JpaJokesService(jpaJokesRepository);
            Jokes expected = jpaJokesService.createJoke(new Jokes());
            List<Jokes> jokes = new ArrayList<>();
            jokes.add(expected);
            assertEquals(jokes, jpaJokesService.findAllJokes());
    }

    @Test
    void updateJokeById(){
            JpaJokesService jpaJokesService = new JpaJokesService(jpaJokesRepository);
            Jokes expected = jpaJokesService.createJoke(new Jokes());
            expected.setLongtext("knock knock");
            jpaJokesService.updateJokeById(expected, expected.getId());
            assertEquals(expected, jpaJokesService.findJokeById(expected.getId()));
    }

    @Test
    void deleteDriverById(){
        JpaJokesService jpaJokesService = new JpaJokesService(jpaJokesRepository);
        Jokes expected = jpaJokesService.createJoke(new Jokes());
        jpaJokesService.deleteJokeById(expected.getId());
        assertNull(jpaJokesService.findJokeById(expected.getId()));
    }
}
