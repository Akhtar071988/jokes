package com.galvanize.jokes.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jokes")
public class Joke {
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setJoke(String longtext) {
        this.joke = longtext;
    }

    public void update(Joke expected) {
        if(expected.getJokeId()!=0)this.setJokeId(expected.getJokeId());
        if(expected.getCategory()!=null)this.setCategory(expected.getCategory());
        if(expected.getJoke()!=null)this.setJoke(expected.getJoke());
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
}
