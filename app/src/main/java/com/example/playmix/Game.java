package com.example.playmix;

public class Game {
    String name;

    public Game(String name) {
        this.name = name;
    }
    public Game(Game other){
        this.name = other.getName();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
