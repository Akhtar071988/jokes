package com.galvanize.jokes.repository;

import com.galvanize.jokes.entity.Category;
import com.galvanize.jokes.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaJokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByCategory(Category category);
    boolean deleteByJokeId(long id);
}
