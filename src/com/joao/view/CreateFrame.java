package com.joao.view;

import com.joao.controller.ConveyanceController;
import com.joao.model.CRUD;
import com.joao.model.Conveyance;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public abstract class CreateFrame<T, E extends CRUD> extends JInternalFrame implements ActionListener {
    private final E controller;
    JPanel panel;
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();

    public CreateFrame(E controller, String[] fields) {
        this.controller = controller;
        this.fields = fields;
        customComponents();
        setVisible(true);
    }

    private void customComponents() {
        // panel
        panel = new JPanel();

        // create, store, and adding components for Labels, textfields, and subPanels
        setLabelsTextFields();

        // Button
        JButton buttonSubmit = new JButton("Guardar");

        // adding Button to panel
        panel.add(buttonSubmit);

        // adding panel
        add(panel);

        // JInternalFrame
        setTitle("CREATE");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Button EventLIstener
        buttonSubmit.addActionListener(this);
    }


    // Methods
    private void setLabelsTextFields() {
        for (String field : fields) {
            subPanels.put(field, new JPanel());
            subPanels.get(field).setLayout(new BoxLayout(subPanels.get(field), BoxLayout.X_AXIS));
            subPanels.get(field).setPreferredSize(new Dimension(300, 30));
            labels.put(field, new JLabel(field));
            textfields.put(field, new JTextField(20));

            // adding to panel
            JPanel paneltxt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            paneltxt.add(textfields.get(field));
            subPanels.get(field).add(labels.get(field));
            subPanels.get(field).add(paneltxt, BoxLayout.Y_AXIS);
            subPanels.get(field).validate();

            // adding subPanel to container (panel)
            panel.add(subPanels.get(field));
        }
    }

    private void checkTextFields() throws Exception {
        for (Map.Entry<String, JTextField> text : textfields.entrySet()) {
            if (text.getValue().getText().equals("")) throw new Exception("You must fill all Fields");
        }
    }
    
    private void tryCreateCar() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields, validate all, and Add new Object T to controller
            T object = getObject();
            controller.validate(object);
            controller.create(object);

            // disable window
            this.dispose();
        } catch (Exception ex) {
            // do Something
            // display error Message;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Car Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage()); // just for now
        }
    }

    protected abstract T getObject();
    @Override
    public void actionPerformed(ActionEvent e) {
        tryCreateCar();
    }

}
