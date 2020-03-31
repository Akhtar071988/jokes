package com.galvanize.jokes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.service.JpaJokeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class JokeControllerTest {
    @Autowired
    MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    JpaJokeService jpaJokeService;

    @Test
    public void createJoke() throws Exception {
        Joke expected = new Joke();
        String json = mapper.writeValueAsString(expected);
        expected.setJokeId(1L);
        when(jpaJokeService.createJoke(any(Joke.class))).thenReturn(expected);
        mvc.perform(post("/api/jokes").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jokeId").value(expected.getJokeId()));
    }

    @Test
    public void getAllJokes() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeService.getAllJokes()).thenReturn(expectedJokes);
        mvc.perform(get("/api/jokes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jokesId").value(expected.getJokeId()));
    }

    @Test
    public void getAllJokesByValue() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        expected.setJoke("Knock knock!");
        expected.setCategory(Category.DADJOKES);
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jpaJokeService.getAllJokesByValue(any(Category.class))).thenReturn(expectedJokes);
        mvc.perform(get("/api/jokes/value?value=DADJOKES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].longtext").value(expected.getJoke()))
                .andExpect(jsonPath("$[0].jokeid").value(expected.getJokeId()))
                .andExpect(jsonPath("$[0].value").value(expected.getCategory().toString()));
    }

    @Test
    public void updateJoke() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        expected.setCategory(Category.DADJOKES);
        Joke patchJoke = new Joke();
        patchJoke.setCategory(Category.DADJOKES);
        String json = mapper.writeValueAsString(patchJoke);
        when(jpaJokeService.update(anyLong(), any(Joke.class))).thenReturn(expected);
        mvc.perform(patch("/api/jokes/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expected));
    }

    @Test
    public void deleteJokeById() throws Exception{
        when(jpaJokeService.deleteById(anyLong())).thenReturn(true);
        mvc.perform(delete("/api/jokes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

}