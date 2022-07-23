package com.joao.view;


import com.joao.model.CRUD;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public abstract class UpdateFrame<T, E extends CRUD<T>>extends JInternalFrame implements ActionListener {
    private final E controller;
    JButton buttonSubmit;
    JButton buttonSearch;
    JPanel panel;
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();
    private JTextField textfieldBuscar;
    private JLabel labelBuscar;


    public UpdateFrame(E controller, String[] fields) {
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
        buttonSubmit = new JButton("Guardar");

        // buscar
        labelBuscar = new JLabel("Ingrese el ID a buscar");
        textfieldBuscar = new JTextField(10);

        // add to panel
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(labelBuscar);
        panelBuscar.add(textfieldBuscar);

        // Button Buscar
        buttonSearch = new JButton("Buscar");

        // adding Button to panel
        panel.add(buttonSubmit);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(300, 5));
        separator.setVisible(true);
        panel.add(separator);
        panel.add(panelBuscar);
        panel.add(buttonSearch);

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

    // Methods
    private void setLabelsTextFields() {
        for (String field : fields) {
            subPanels.put(field, new JPanel());
            labels.put(field, new JLabel(field));
            textfields.put(field, new JTextField(20));

            // adding to panel
            subPanels.get(field).add(labels.get(field));
            subPanels.get(field).add(textfields.get(field));

            // adding subPanel to container (panel)
            panel.add(subPanels.get(field));
        }
    }

    protected abstract void setTextfieldValue(T obj);

    private void checkTextFields() throws Exception {
        for (Map.Entry<String, JTextField> text : textfields.entrySet()) {
            if (text.getValue().getText().equals("")) throw new Exception("You must fill all Fields");
        }
    }
    
    private void tryUpdateObject() {
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
                setTextfieldValue(object);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ID no existe", "Car Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == buttonSubmit) tryUpdateObject();
    }

}