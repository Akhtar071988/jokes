package com.galvanize.jokes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.jokes.entity.Jokes;
import com.galvanize.jokes.service.JpaJokesService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class JokesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JpaJokesService jpaJokesService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createAJoke() throws Exception {
        Jokes jokes = new Jokes();
        String json = objectMapper.writeValueAsString(jokes);
        jokes.setId(1L);
        when(jpaJokesService.createJoke(ArgumentMatchers.any(Jokes.class))).thenReturn(jokes);
        mockMvc.perform(post("/api/jokes").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jokes.getId()));
    }

    @Test
    void findAJoke() throws Exception {
        Jokes jokes = new Jokes();
        jokes.setId(1L);
        when(jpaJokesService.findJokeById(1L)).thenReturn(jokes);
        mockMvc.perform(get("/api/jokes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jokes.getId()));
    }

    @Test
    void findAllJokes() throws Exception {
        List<Jokes> jokes = new ArrayList<>();
        Jokes jokes1 = new Jokes();
        jokes.add((Jokes) jokes);
        when(jpaJokesService.findAllJokes()).thenReturn(jokes);
        mockMvc.perform(get("/api/jokes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateJokeById() throws Exception{
        Jokes expected = new Jokes();
        String json = objectMapper.writeValueAsString(expected);
        expected.setId(1L);
        when(jpaJokesService.updateJokeById(ArgumentMatchers.any(Jokes.class),ArgumentMatchers.anyLong())).thenReturn(expected);
        mockMvc.perform(put("/api/jokes/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()));
    }

    @Test
    void deleteJokeById() throws Exception{
        mockMvc.perform(delete("/api/jokes/1"))
                .andExpect(status().isOk());
    }
}
