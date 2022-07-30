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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
* JInternalFrame/Ventana customizable para Modifcar Objetos de tipo {@link Class}
 * almacenados en el {@link #controller}. <br/>
 * crea los textfields, labels y tablas a desplegar en base al array de
 * Strings, {@link #fields}, que recibe en el constructor {@link #UpdateFrame(com.joao.model.CRUD, String[])}
 * para luego manipular los inputs con el {@link #controller} <br/><br/>
 * @param <Class> La clase del Objeto a Modificar del {@link #controller}
 * @param <Controlador>> La Clase del {@link #controller} esta clase
 * debe implementar la interface {@link com.joao.model.CRUD} con parametro del
 * mismo tipo que {@link java.lang.Class}
 * @WARNING tomar en cuenta que en base al Array de String que se recibe en el constructor
 * {@link #UpdateFrame(com.joao.model.CRUD, String[])}
 * se espera que se pueda obtener todos los datos necesarios para Crear y/o modicar
 * un Objeto tipo {@link Class} y luego manipularle con el controlador
 * @see #UpdateFrame(com.joao.model.CRUD, String[])
* */

public abstract class UpdateFrame<Class, Controlador extends CRUD<Class>>extends JInternalFrame implements ActionListener {
    protected final Controlador controller;
    JButton buttonSubmit = new JButton("Guardar");
    JButton buttonSearch = new JButton("Buscar");
    JPanel panel = new JPanel();
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();
    private final JTextField textfieldBuscar = new JTextField(10);
    private final JLabel labelBuscar = new JLabel("Ingrese el ID a buscar");


    /**
     * @param controller Una instancia (de preferencia singleton) que debe implementar
     * {@link com.joao.model.CRUD}. <br/>
     * con esta se validara, y modificara objetos de tipo {@link Class}
     * almacenados en el {@link #controller}
     * @param fields Array con el nombre de todas las propiedades de un Objeto {@link Class} que
     * seran usadas para crear Labels con cada uno de los valores del array e
     * instanciar un Objeto con el input de cada label. <br/>
     * Cada valor del array es utilizado como Key de los HashMap
     * {@link #textfields}, {@link #labels}, {@link #subPanels}
     * para más información de ese proceso ver {@link #setLabelsTextFields()}
     * en alguna de las implementaciones
     * @see #setLabelsTextFields()
     * */
    public UpdateFrame(Controlador controller, String[] fields) {
        this.controller = controller;
        this.fields = fields;
        customComponents();
        setVisible(true);
    }

    /**
     * establece los paneles y todo lo necesario del frame
     * */
    private void customComponents() {
        // crea y agrega el panel Buscar
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

    /**
     * crea y agrega el panel Buscar a {@link #panel}
     * */
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
   /**
     * Crea, centra y agrega a {@link #panel} cada una de las Labels con combre en base a cada uno de los valores
     * {@link #fields}
     * puedes acceder a cada uno de las labels, textfields o subpanels con
     * {@link #labels}, {@link #textfields},
     * {@link #subPanels} respectivamente, ingresando
     * el nombre del field como key en el HashMap
     * */
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

            // adding to panel
            panel.add(subPanels.get(field));

            // adding buttonSubmit
            panel.add(buttonSubmit);
            revalidate();
            repaint();
        }
    }

    /**
     * establece los datos actuales del objeto recibido en los TextFields para su posterior modificación
     * @param obj el objeto el cual se le mostrara sus propiedades y sera
     * sobreescrito en el {@link #controller}
     * */
    protected abstract void setTextfieldValue(Class obj);

     /**
     * Revisa que todos los TextFields están llenos
     * */
    protected void checkTextFields() throws Exception {
        for (Map.Entry<String, JTextField> text : textfields.entrySet()) {
            if (text.getValue().getText().equals("")) throw new Exception("You must fill all Fields");
        }
    }
    
     /**
     * Intenta crear un Objeto de clase {@link Class}
     * hace uso de {@link #controller} para validar todo los datos y crear el Objeto. <br/>
     * después sobreescribe el objeto almacenado en {@link #controller} con el mismo id.
     * luego de esta operación la Ventana se cerrara. <br/>
     * en caso de Error desplegara una Ventana {@link javax.swing.JOptionPane} con el error
     * y no se sobreescribirá ningún objeto
     * @WARNING esta ventana se cerrará, después de esta operación, no solo se esconderá. <br/>
     * se hace uso de {@link #dispose()} para esta operación
     * */
    protected void tryUpdateObject() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields, validate all, and Add new Object T to controller
            Class object = getObject();
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

    /**
     * Crea un Objeto de tipo {@link Class } en base a los valores escritos en los {@link #textfields}. <br/>
     * Este metodo será implementado distinto por cada Clase que herede de esta
     * @IMPORTANT Este metodo debería ser llamado después de {@link #checkTextFields()} para asegurar que
     * todos los campos necesarios están llenos
     * */
    protected abstract Class getObject();

     /**
     * maneja los eventos de {@link #buttonSearch} y {@link #buttonSubmit} <br/>
     *
     * @buttonSerch Verifica que el ID ingresado sea válido y exista en el {@link #controller},
     * luego despliega los textFields con los datos del objeto buscado en el
     * {@link #controller}. <br/>
     * en caso de error despliega una Ventana explicando el error y no despliega ningún textField
     * @buttonDelete Llama a {@link #tryUpdateObject()} e intenta sobreescribir el objeto con el ID
     * indicado en el {@link #controller}. <br/>
     * en caso de error en el proceso se desplegará una ventana explicando el error y no sobreescribirá nada
     * */
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
                Class object = controller.read(id);
                if (object == null) throw new Exception("ID no existe");
                panel.removeAll();
                setLabelsTextFields();
                setTextfieldValue(object);
                setPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == buttonSubmit) tryUpdateObject();
    }

}