package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

public class DeleteUser extends DeleteFrame<User, UserController>{
    public DeleteUser() {
        super(new String[] {"ID", "Correo", "Rol"});
    }

    @Override
    protected void setTextfieldValue(User user) {
        String[] array = data[0];
        array[0] = String.valueOf(user.getId());
        array[1] = String.valueOf(user.getEmail());
        array[2] = String.valueOf(user.getRol());
    }
}
