package com.galvanize.jokes.controller;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.service.JpaJokeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joke")
public class JokeController {
        JpaJokeService jpaJokeService;

        public JokeController(JpaJokeService jpaJokeService) {
            this.jpaJokeService = jpaJokeService;
        }

        @PostMapping
        public Joke createJoke(@RequestBody Joke input) {
            return jpaJokeService.createJoke(input);
        }

        @GetMapping
        public List<Joke> getAllJokes() {
            return jpaJokeService.getAllJokes();
        }

        @GetMapping("/category")
         public List<Joke> getAllJokesByCategory(@RequestParam Category category){
            return jpaJokeService.getAllJokesByCategory(category);
        }

        @PatchMapping("/{id}")
        public Joke updateJoke(@PathVariable long id, @RequestBody Joke updateJoke){
            return jpaJokeService.update(id, updateJoke);
        }

        @DeleteMapping("/{id}")
        public boolean updateJoke(@PathVariable long id){
        return jpaJokeService.deleteById(id);
    }
}
