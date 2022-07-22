package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

public class CreateFrameUserAdmin extends CreateFrame<User, UserController>{
    public CreateFrameUserAdmin() {
        super(new UserController(), new String[] {"Correo", "Contraseña", "Rol"});
    }

    @Override
    protected User getObject() {

        String email = textfields.get("Correo").getText();
        String password = textfields.get("Contraseña").getText();
        char rol = textfields.get("Rol").getText().charAt(0);
        return new User(0, email, password, rol);
    }
}
