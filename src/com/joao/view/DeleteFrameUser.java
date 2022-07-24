package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

public class DeleteFrameUser extends DeleteFrame<User, UserController>{
    public DeleteFrameUser() {
        super(new UserController(), new String[] {"ID", "Correo", "Contraseña", "Rol"});
    }

    @Override
    protected void setDataTableValue(User user) {
        String[] array = data[0];
        array[0] = String.valueOf(user.getId());
        array[1] = String.valueOf(user.getEmail());
        array[2] = String.valueOf(user.getPassword());
        array[3] = String.valueOf(user.getRol());
    }
}
