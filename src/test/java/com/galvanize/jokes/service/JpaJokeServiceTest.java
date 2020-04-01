package com.galvanize.jokes.service;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.repository.JpaJokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JpaJokeServiceTest {
    @MockBean
    JpaJokeRepository jpaJokeRepository;

    @Autowired
    JpaJokeService jpaJokeService;

    @Test
    public void createJoke(){
        Joke input = new Joke(Category.DADJOKES,"Knock knock!");
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        when(jpaJokeRepository.save(any(Joke.class))).thenReturn(expected);
        assertEquals(expected, jpaJokeService.createJoke(input));
    }

    @Test
    public void getAllJokes(){
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeRepository.findAll()).thenReturn(expectedJokes);
        assertEquals(expectedJokes, jpaJokeService.getAllJokes());
    }

    @Test
    public void getAllJokesByCategory(){
        JpaJokeService jpaJokeService = new JpaJokeService(jpaJokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Knock knock!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeRepository.findAllByCategory(any(Category.class))).thenReturn(expectedJokes);
        assertEquals(expectedJokes, jpaJokeService.getAllJokesByCategory(Category.DADJOKES));
    }

    @Test
    public void updateJoke(){
        Joke preUpdateJoke = new Joke(1L, Category.KIDJOKES,"Knock knock!");
        Joke expected = new Joke(preUpdateJoke.getJokeId(), Category.DADJOKES,preUpdateJoke.getJoke());
        Joke updateJoke = new Joke();
        updateJoke.setCategory(expected.getCategory());
        when(jpaJokeRepository.findById(anyLong())).thenReturn(Optional.of(preUpdateJoke));
        assertEquals(expected, jpaJokeService.update(preUpdateJoke.getJokeId(), updateJoke));
    }

    @Test
    public void deleteJokeById(){
        when(jpaJokeRepository.deleteByJokeId(anyLong())).thenReturn(true);
        assertTrue(jpaJokeService.deleteById(1L));
    }
}