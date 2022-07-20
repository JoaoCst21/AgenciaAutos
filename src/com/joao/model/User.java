package com.joao.model;

public class User {
    private int id;
    private String email;
    private String password;
    private char rol;

    public User(int id, String email, String password, char rol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public char getRol() {
        return rol;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }
}
