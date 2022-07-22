package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

public class CreateFrameUserWorker extends CreateFrame<User, UserController> {
    public CreateFrameUserWorker() {
        super(new UserController(), new String[] {"Correo", "Contraseña"});
    }

    @Override
    protected User getObject() {
        String email = textfields.get("Correo").getText();
        String password = textfields.get("Contraseña").getText();
        return new User(0, email, password, 'B');
    }
}
