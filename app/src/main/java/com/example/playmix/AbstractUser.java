package com.example.playmix;

public class AbstractUser {

    protected static final long FREE_USER=0;
    protected static final long GOLD_USER=1;
    protected static final long PLATINUM_USER=2;
    private String email;
    private String name;
    private long status;
    private String id;

    public AbstractUser() {
    }

    public AbstractUser(String email, String name) {
        this.email = email;
        this.name = name;
        this.status = FREE_USER;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
