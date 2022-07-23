package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

public class UpdateFrameUser extends UpdateFrame<User, UserController>{

    UpdateFrameUser() {
        super(new UserController(), new String[] {"Correo", "Contraseña", "Rol"});
    }

    @Override
    protected void setTextfieldValue(User user) {
        textfields.get("Correo").setText(String.valueOf(user.getEmail()));
        textfields.get("Contraseña").setText(String.valueOf(user.getPassword()));
        textfields.get("Rol").setText(String.valueOf(user.getRol()));
    }

    @Override
    protected User getObject() {
        String email = textfields.get("Correo").getText();
        String password = textfields.get("Contraseña").getText();
        char rol = textfields.get("Rol").getText().charAt(0);
        return new User(0, email, password, rol);
    }
}
