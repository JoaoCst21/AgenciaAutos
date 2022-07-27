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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* JInternalFrame/Ventana customizable para eliminar Objetos de tipo {@link Class}
 * almacenados en el {@link #controller} especificado en el constructor
 * {@link #DeleteFrame(com.joao.model.CRUD, String[])}. <br/>
 * crea las columnas de la tabla a desplegar en base al array de
 * String que recibe en el Constructor.
 * @param <Class> La clase del Objeto a Eliminar del {@link #controller}
 * @param <Controlador>> La Clase del {@link #controller} esta clase
 * debe implementar la interface {@link com.joao.model.CRUD} con parametro del
 * mismo tipo que {@link Class}
* */
public abstract class DeleteFrame<Class, Controlador extends CRUD<Class>> extends JInternalFrame implements ActionListener {
    private Controlador controller;
    String[][] data;
    Class object;
    String[] column;
    private JLabel labelBuscar;
    private JTextField textfieldBuscar;
    private JButton buttonSearch;
    private JButton buttonDelete = new JButton("Delete");

    /**
     * @param controller Una instancia (de preferencia singleton) que debe implementar
     * {@link com.joao.model.CRUD}. <br/>
     * con esta se validara, recibira y eliminara objetos de tipo {@link Class} se espera que estos
     * objetos esten almacenados en el {@link #controller}
     * @param column Array con el nombre de todas las propiedades de un Objeto {@link Class} que
     * seran usadas para crear los nombres de las columnas de la tabla
     * */
    public DeleteFrame(Controlador controller, String[] column) {
        data = new String[1][column.length];
        this.controller = controller;
        this.column = column;
        customComponents();
        setVisible(true);
    }

    /**
     * establece los paneles y todo lo necesario del frame
     * */
    private void customComponents() {
       JPanel panel = getPanel();
       add(panel);

        // JInternal Frame
        setTitle("DELETE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Crea un panel con un text-field para ingresar y un botón para buscar
     * */
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

    /**
     * maneja los eventos de {@link #buttonSearch} y {@link #buttonDelete} <br/>
     *
     * @buttonSerch verifica que el ID ingresado sea valido y exista en el {@link #controller},
     * luego despliega una tabla  listando las propiedades del objeto almacenado em
     * {@link #controller}. <br/>
     * en caso de error despliega una Ventana explicando el error y no despliega ninguna tabla
     * @buttonDelete llama a {@link #tryDeleteObject()} e intenta eliminar el objeto con el ID
     * indicado del {@link #controller}
     * y luego despliega una ventana preguntando si desea eliminarlo. <br/>
     * en caso de error en el proceso se desplagara una ventana explicando el error y no elimina nada
     * */
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
            // Panel all
            JPanel panelParent = new JPanel();

            // ad to panel
            panelParent.add(getTable());
            panelParent.add(getPanel());

            add(panelParent);
            revalidate();
            repaint();
        }
        if (e.getSource() == buttonDelete) tryDeleteObject();

    }
    /**
     * Crea una Tabla con los valores del array {@link #column} como columnas
     * y un boton para eliminar el objeto mostrado del {@link #controller}
     * */
    private JPanel getTable() {
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
        return panelTable;
    }

    /**
     * valida e intenta eliminar el valor actual de {@link #object} de {@link #controller}
     * en caso de error desplega una ventana explicando el error y cancela el proceso
     * de eliminación
     * */
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

    /**
     * Deconstruye el Objeto recibido y muestra sus propiedades en la tabla
     * */
    protected abstract void setDataTableValue(Class obj);

    /**
     * Verifica que todos los Campos requeridos necesarios esten completos
     * */
    private void checkTextFields() throws Exception {
            if (labelBuscar.getText().equals("")) throw new Exception("You must fill all Fields");
    }
}
