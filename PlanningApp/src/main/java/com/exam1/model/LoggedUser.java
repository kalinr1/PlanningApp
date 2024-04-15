package com.exam1.model;

public class LoggedUser {

    private Long id;
    private String username;

    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogged (){
        return this.id != null && this.username != null;
    }

}
