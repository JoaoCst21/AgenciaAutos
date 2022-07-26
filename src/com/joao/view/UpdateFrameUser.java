package com.joao.view;

import com.joao.controller.UserController;
import com.joao.model.User;

import javax.swing.JOptionPane;

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

    @Override
    protected void tryUpdateObject() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields, validate all, and Add new Object T to controller
            User user = getObject();
            controller.validateEmail(user.getEmail());
            controller.validatePassword(user.getPassword());
            controller.validateRol(user.getRol());
            controller.update(user);

            // disable window
            this.dispose();
        } catch (Exception ex) {
            // display error Message;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Car Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
