package com.joao.view;

import com.joao.controller.UserController;

public class ReadFrameUser extends ReadFrame{
    UserController userController = new UserController();

    public ReadFrameUser() {
        super(new String[] {"Correo", "Rol"}, UserController.getFields());
    }
}
