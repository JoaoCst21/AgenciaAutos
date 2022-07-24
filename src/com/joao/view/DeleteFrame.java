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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DeleteFrame<T, E extends CRUD<T>> extends JInternalFrame implements ActionListener {
    private E controller;
    String[][] data;
    T object;
    String[] column;
    private JLabel labelBuscar;
    private JTextField textfieldBuscar;
    private JButton buttonSearch;
    private JButton buttonDelete = new JButton("Delete");

    public DeleteFrame(E controller, String[] column) {
        data = new String[1][column.length];
        this.controller = controller;
        this.column = column;
        customComponents();
        setVisible(true);
    }

    private void customComponents() {
       JPanel panel = getPanel();
       add(panel);

        // JInternal Frame
        setTitle("DELETE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel getPanel() {
         // buscar
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        labelBuscar = new JLabel("Ingrese el ID a buscar");
        textfieldBuscar = new JTextField(10);

        // Button Buscar
        buttonSearch = new JButton("Buscar");
        buttonSearch.addActionListener(this);
        JPanel panelButton = new JPanel();
        panelButton.add(buttonSearch);

        // add to panel
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(labelBuscar);
        panelBuscar.add(textfieldBuscar);
        panelBuscar.setPreferredSize(new Dimension(300, 100));

        // add button and Panel
        panel.add(panelBuscar);
        panel.add(panelButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSearch) {
            int id;
            try {
                id = Integer.parseInt(textfieldBuscar.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un numero Valido", "Delete Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                object = controller.read(id);
                System.out.println(object);
                setDataTableValue(object);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "El ID no se encuentra", "Delete Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.getContentPane().removeAll();

            // panel
            JPanel panelTable = new JPanel(new GridLayout(2,1, 10, 30));

            // Table
            JTable table = new JTable(data, column);
            table.setBounds(30, 40, 200, 300);
            table.setPreferredSize(new Dimension(getSize().width, 15));
            table.setPreferredScrollableViewportSize(new Dimension(getSize().width - 20, 15));

            // ScrollPane
            JScrollPane sp =new JScrollPane(table);

            // button delete action listener
            JPanel panelButton = new JPanel();
            panelButton.add(buttonDelete);
            buttonDelete.addActionListener(this);

            // add
            panelTable.add(sp);
            panelTable.add(panelButton);

            // Panel all
            JPanel panelParent = new JPanel();

            panelParent.add(panelTable);
            panelParent.add(getPanel());

            add(panelParent);
            revalidate();
            repaint();
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
    protected abstract void setDataTableValue(T obj);
    private void checkTextFields() throws Exception {
            if (labelBuscar.getText().equals("")) throw new Exception("You must fill all Fields");
    }
}
