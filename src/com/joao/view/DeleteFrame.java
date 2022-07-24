package com.joao.view;

import com.joao.model.CRUD;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class DeleteFrame<T, E extends CRUD<T>> extends JInternalFrame implements ActionListener {
    private E controller;
    String[][] data;
    T object;
    String[] column;
    private JLabel labelBuscar;
    private JTextField textfieldBuscar;
    private JButton buttonSearch;
    private JButton buttonDelete = new JButton("Delete");

    public DeleteFrame(String[] column) {
        this.column = column;
        customComponents();
        setVisible(true);
    }

    private void customComponents() {
        // buscar
        labelBuscar = new JLabel("Ingrese el ID a buscar");
        textfieldBuscar = new JTextField(10);

        // add to panel
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(labelBuscar);
        panelBuscar.add(textfieldBuscar);

        // Button Buscar
        buttonSearch = new JButton("Buscar");

        // add button and Panel
        add(panelBuscar);
        add(buttonSearch);

        // JInternal Frame
        setTitle("UPDATE");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSearch) {
            int id = Integer.parseInt(textfieldBuscar.getText());
            object = controller.read(id);
            setTextfieldValue(object);

            // Table
            JTable table = new JTable(data, column);
            table.setBounds(30, 40, 200, 300);

            // ScrollPane
            JScrollPane sp=new JScrollPane(table);

            // add table to frame
            add(sp);

            add(buttonDelete);
        }
        if (e.getSource() == buttonDelete) tryDeleteObject();

    }

    private void tryDeleteObject() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields and Add new Object T to controller
            String message = "Estas seguro que deseas Eliminar este objeto?, esta accion no se puede deshacer";
            JOptionPane.showMessageDialog(null, message, "Seguro?", JOptionPane.WARNING_MESSAGE);
            controller.delete(object);

            // disable window
            this.dispose();
        } catch (Exception ex) {
            // display error Message;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Deleting", JOptionPane.ERROR_MESSAGE);
        }
    }
    protected abstract void setTextfieldValue(T obj);
    private void checkTextFields() throws Exception {
            if (labelBuscar.getText().equals("")) throw new Exception("You must fill all Fields");
    }
}
