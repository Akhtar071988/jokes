package com.galvanize.jokes.controller;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.service.JpaJokeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {
        JpaJokeService jpaJokeService;

        public JokeController(JpaJokeService jpaJokeService) {
            this.jpaJokeService = jpaJokeService;
        }

        @PostMapping("/jokes")
        public Joke createJoke(@RequestBody Joke jokes) {
            return jpaJokeService.createJoke(jokes);
        }

        @GetMapping("/jokes")
        public List<Joke> findAllJokes() {
            return jpaJokeService.getAllJokes();
        }

        @GetMapping("/value")
         public List<Joke> getAllJokesByValue(@RequestParam Category jokesValue){
            return jpaJokeService.getAllJokesByValue(jokesValue);
        }

        @PatchMapping("/{id}")
        public Joke updateJoke(@PathVariable long id, @RequestBody Joke updateJoke){
            return jpaJokeService.update(id, updateJoke);
        }

        @DeleteMapping("jokes/{id}")
        public void deleteJokeById(@PathVariable Long id){ jpaJokeService.deleteById(id); }
}
