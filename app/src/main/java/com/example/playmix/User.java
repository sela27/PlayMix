package com.example.playmix;

public class User extends AbstractUser {

    public User(String email, String name) {
        super(email, name);
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
