package com.galvanize.jokes.controller;

import com.galvanize.jokes.entity.Jokes;
import com.galvanize.jokes.service.JpaJokesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
public class JokesController {
        JpaJokesService jpaJokesService;

        public JokesController(JpaJokesService jpaJokesService) {
            this.jpaJokesService = jpaJokesService;
        }

        @PostMapping("/jokes")
        public Jokes createJoke(@RequestBody Jokes jokes) {
            return jpaJokesService.createJoke(jokes);
        }

        @GetMapping("/jokes/{id}")
        public Jokes findJoke(@PathVariable Long id) {
            return jpaJokesService.findJokeById(id);
        }

        @GetMapping("/jokes")
        public List<Jokes> findAllJokes() {
            return jpaJokesService.findAllJokes();
        }

        @PutMapping("/jokes/{id}")
        public Jokes updateJoke(@PathVariable Long id, @RequestBody Jokes jokes){
            return jpaJokesService.updateJokeById(jokes, id);}

        @DeleteMapping("jokes/{id}")
        public void deleteJokeById(@PathVariable Long id){ jpaJokesService.deleteJokeById(id); }
}
