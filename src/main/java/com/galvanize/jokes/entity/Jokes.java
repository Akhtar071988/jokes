package com.galvanize.jokes.entity;

import javax.persistence.*;

@Entity
@Table(name="jokes")
public class Jokes {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @Column
        long jokesid;
        @Column
        @Enumerated(EnumType.STRING)
        Value value;
        @Column
        String longtext;

    public Jokes() {
    }

    public Jokes(Long id, Long jokesid, Value value, String longtext) {
        this.id = id;
        this.jokesid = jokesid;
        this.value = value;
        this.longtext = longtext;
    }

    public long getJokesid() {
        return jokesid;
    }

    public void setJokesid(long jokesid) {
        this.jokesid = jokesid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getLongtext() {
        return longtext;
    }

    public void setLongtext(String longtext) {
        this.longtext = longtext;
    }

    public void update(Jokes expected) {
        if(expected.getId()!=0)this.setId(expected.getId());
        if(expected.getJokesid()!=0)this.setJokesid(expected.getJokesid());
        if(expected.getValue()!=null)this.setValue(expected.getValue());
        if(expected.getLongtext()!=null)this.setLongtext(expected.getLongtext());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
