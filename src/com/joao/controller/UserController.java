package com.joao.controller;

import com.joao.model.CRUD;
import com.joao.model.User;

import java.util.ArrayList;

public class UserController implements CRUD<User> {
    private static ArrayList<User> users = new ArrayList<>() {{
        add(new User(0, "joao@kinal.edu.gt", "123456", 'A'));
        add(new User(1, "david@kinal.edu.gt", "123456", 'B'));
    }};
    private static int id = users.size();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static String[][] getFields() {
        String[][] array = new String[users.size()][3];
        int i = 0;
        for (User user : users) {
            array[i][0] = String.valueOf(user.getId());
            array[i][1] = String.valueOf(user.getEmail());
            if (user.getRol() == 'A') array[i][2] = "Admin";
            if (user.getRol() == 'B') array[i][2] = "Worker";
            i++;
        }

        return array;
    }

    public void validateEmail(String email) throws Exception {
        for (int i = 0; i < email.length(); i++)
            if (email.charAt(i) == '@') return;

        throw new Exception("Email no contiene \'@\'");
    }

    public void validatePassword(String password) throws Exception {
         if (password.length() < 4) {
            throw new Exception("La contraseña es Invalida, se necesita al menos 4 caracteres");
        }
    }

    public char verify(String email, String password) {
        for (User user: users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) return user.getRol();
        }

        try {
            validateEmail(email);
            validatePassword(password);
        } catch (Exception e) {
            // throw new RuntimeException(e);
            // add JFrame displaying error
            return 'F';
        }

        // Display Error Message;
        // it is not in our Database;
        return 'F';
    }

    public void validateRol(char rol) throws Exception{
        if (rol == 'A' || rol == 'B') return;
        throw new Exception("Rol no valid, must be \'A\' or \'B\'");
    }

    public void validateNoRepeatedEmail(String email) throws Exception {
        for (User user : users) {
            if (user.getEmail().equals(email)) throw new Exception("Email is in database already");
        }
    }



    // Interface Methods
    @Override
    public void create(User user) {
            user.setId(id);
            users.add(user);
            id++;
    }

    @Override
    public User read(int id) {
        for (User user : users)
            if (user.getId() == id) return user;

        return null;
    }

    @Override
    public void update(User user) {
        for (User usuario : users)
            if (usuario.getId() == user.getId()) {
                users.remove(usuario);
                users.add(user);
                return;
            }
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void validate(User user) throws Exception {
        validateNoRepeatedEmail(user.getEmail());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateRol(user.getRol());
    }
}
