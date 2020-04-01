package com.galvanize.jokes.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "joke")
public class Joke {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private long jokeId;
        @Column(name = "category")
        @Enumerated(EnumType.STRING)
        private Category category;
        @Column(name = "joke")
        private String joke;

    public Joke() {
    }

    public Joke(long jokeId, Category category, String joke) {
        this.jokeId = jokeId;
        this.category = category;
        this.joke = joke;
    }

    public Joke(Category category, String joke){
        this.category = category;
        this.joke = joke;
    }

    public long getJokeId() {
        return jokeId;
    }

    public void setJokeId(long jokeId) {
        this.jokeId = jokeId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke1 = (Joke) o;
        return getJokeId() == joke1.getJokeId() &&
                getCategory() == joke1.getCategory() &&
                Objects.equals(getJoke(), joke1.getJoke());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJokeId(), getCategory(), getJoke());
    }

    public void update(Joke updateJoke){
        if(updateJoke.getCategory()!=null)this.setCategory(updateJoke.getCategory());
        if(updateJoke.getJokeId()!=0)this.setJokeId(updateJoke.getJokeId());
        if(updateJoke.getJoke()!=null)this.setJoke(updateJoke.getJoke());
    }
}