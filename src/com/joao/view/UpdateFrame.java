package com.joao.view;


import com.joao.model.CRUD;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public abstract class UpdateFrame<T, E extends CRUD<T>>extends JInternalFrame implements ActionListener {
    protected final E controller;
    JButton buttonSubmit = new JButton("Guardar");
    JButton buttonSearch = new JButton("Buscar");
    JPanel panel = new JPanel();
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();
    private JTextField textfieldBuscar = new JTextField(10);
    private JLabel labelBuscar = new JLabel("Ingrese el ID a buscar");


    public UpdateFrame(E controller, String[] fields) {
        this.controller = controller;
        this.fields = fields;
        customComponents();
        setVisible(true);
    }


    private void customComponents() {
        // create, and store components for Labels, textfields, and subPanels
        setLabelsTextFields();

        setPanel();

        // adding panel
        add(panel);

        // JInternalFrame
        setTitle("UPDATE");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Button EventLIstener
        buttonSubmit.addActionListener(this);
        buttonSearch.addActionListener(this);
    }

    private void setPanel() {
        // add to panel
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(labelBuscar);
        panelBuscar.add(textfieldBuscar);

        // adding Button to panel
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(300, 5));
        separator.setVisible(true);
        panel.add(separator);
        panel.add(panelBuscar);
        panel.add(buttonSearch);
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
        }
    }

    private void addSubPanels() {
        for (String field : fields)
            panel.add(subPanels.get(field));

        panel.add(buttonSubmit);
        revalidate();
        repaint();
    }

    protected abstract void setTextfieldValue(T obj);

    protected void checkTextFields() throws Exception {
        for (Map.Entry<String, JTextField> text : textfields.entrySet()) {
            if (text.getValue().getText().equals("")) throw new Exception("You must fill all Fields");
        }
    }
    
    protected void tryUpdateObject() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields, validate all, and Add new Object T to controller
            T object = getObject();
            controller.validate(object);
            controller.update(object);

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
        if (e.getSource() == buttonSearch) {
            int id;
            try {
                id = Integer.parseInt(textfieldBuscar.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ID debe de ser un numero", "Car Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            try {
                T object = controller.read(id);
                if (object == null) throw new Exception("ID no existe");
                panel.removeAll();
                setTextfieldValue(object);
                addSubPanels();
                setPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == buttonSubmit) tryUpdateObject();
    }

}