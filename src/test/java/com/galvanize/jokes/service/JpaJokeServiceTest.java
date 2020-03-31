package com.galvanize.jokes.service;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.repository.JpaJokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JpaJokeServiceTest {
    @MockBean
    JpaJokeRepository jpaJokeRepository;

    @Test
    public void createJoke(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        Joke input = new Joke(Category.DADJOKES,"Knock knock!");
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        when(jpaJokeRepository.save(any(Joke.class))).thenReturn(expected);
        assertEquals(expected, jpaJokeService.createJoke(input));
    }

    @Test
    public void getAllJokes(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeRepository.findAll()).thenReturn(expectedJokes);
        assertEquals(expectedJokes, jpaJokeService.getAllJokes());
    }

    @Test
    public void getAllJokesByJokesValue(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeRepository.findAllByValue(any(Category.class))).thenReturn(expectedJokes);
        assertEquals(expectedJokes, jpaJokeService.getAllJokesByValue(Category.DADJOKES));
    }

    @Test
    public void updateJoke(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        Joke preUpdateJoke = new Joke(1L, Category.KIDJOKES,"Knock knock!");
        Joke expected = new Joke(preUpdateJoke.getJokeId(), Category.DADJOKES,preUpdateJoke.getJoke());
        Joke updateJoke = new Joke();
        updateJoke.setCategory(expected.getCategory());
        when(jpaJokeRepository.findById(anyLong())).thenReturn(Optional.of(preUpdateJoke));
        assertEquals(expected, jpaJokeService.update(preUpdateJoke.getJokeId(), updateJoke));
    }

    @Test
    public void deleteJokeById(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        when(jpaJokeRepository.deleteByJokeId(anyLong())).thenReturn(true);
        assertTrue(jpaJokeService.deleteById(1L));
    }
}