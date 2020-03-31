package com.galvanize.jokes.repository;

import com.galvanize.jokes.entity.Joke;
import com.galvanize.jokes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaJokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByValue(Category jokesValue);
    boolean deleteByJokeId(long id);
}
