package com.example.playmix;

import java.util.LinkedList;

public class User extends AbstractUser {
    LinkedList<Game> myGames;

    public User(String email, String name) {
        this.setEmail(email);
        this.setName(name);
        myGames=new LinkedList<>();
    }

    public User() {
    }
    void upgrade_gold(){
        this.setStatus(GOLD_USER);

    }
    void upgrade_platinum(){
        this.setStatus(PLATINUM_USER);
    }
}
