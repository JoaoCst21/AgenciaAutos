package com.joao.view;

import com.joao.controller.UserController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * JFrame/Ventana de Inicio de Sesion que pregunta los datos al Usuario, y los inicia en la aplicación
 * */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        customComponents();
        setVisible(true);
    }

    /**
     * establece los paneles y todo lo necesario del frame
     * */
    private void customComponents() {
        // declaring parent Panel
        JPanel parentPanel = new JPanel();
        parentPanel.setPreferredSize(new Dimension(320, 120));

        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 5, 10));

        // subpanels
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));

        // labels
        JLabel emailLabel = new JLabel("Correo :");
        JLabel passwordLabel = new JLabel("Contraseña :");

        // textFields
        JPanel emailTxtPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField emailTxt = new JTextField(20);
        emailTxtPanel.add(emailTxt);
        JPanel passwordTxtPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField passwordTxt = new JTextField(20);
        passwordTxtPanel.add(passwordTxt);

        // adding labels and textfields to subpanels
        emailPanel.add(emailLabel, BoxLayout.X_AXIS);
        emailPanel.add(emailTxtPanel, BoxLayout.Y_AXIS);

        passwordPanel.add(passwordLabel, BoxLayout.X_AXIS);
        passwordPanel.add(passwordTxtPanel, BoxLayout.Y_AXIS);

        // Buttons
        JButton buttonSubmit = new JButton("Iniciar Sesion");

        // adding subPanels to panels
        panel.add(emailPanel);
        panel.add(passwordPanel);

        // adding panel to ParentPanel
        parentPanel.add(panel);

        // adding button to panel
        parentPanel.add(buttonSubmit);

        // adding parentPanel to Frame
        add(parentPanel);

        // frame
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // setting button EventListener
        buttonSubmit.addActionListener(e -> {
            // validate, log, and call MainFrame;
            UserController userController = new UserController();
            char rol = userController.verify(emailTxt.getText(), passwordTxt.getText());
            if (rol == 'A') {
                this.dispose();
                new MainFrameAdmin();
                return;
            }
            if (rol == 'B') {
                this.dispose();
                new MainFrameWorker();
                return;
            }
            // Display Error
            try {
                userController.validateEmail(emailTxt.getText());
                userController.validatePassword(passwordTxt.getText());
                throw new Exception("Usuario o contraseña incorrecta");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
