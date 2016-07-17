package com.aleds90.android.pokemonhelper.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String name;
    private int cp;
    private Gym gym;

    public Pokemon(){}

    public Pokemon(String name, int cp, Gym gym){
        this.name = name;
        this.cp = cp;
        this.gym = gym;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
