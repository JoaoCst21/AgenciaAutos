package com.joao.view;

import com.joao.model.CRUD;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


/**
* JInternalFrame/Ventana customizable para crear y almaceanr Objetos de tipo {@link Class}
 * en el {@link #controller} <br/>
 * crea los textfields y labels en base al array de
 * Strings, {@link #fields}, que recibe en el constructor {@link #CreateFrame(com.joao.model.CRUD, String[])}
 * para luego manipular los inputs con el {@link #controller} <br/><br/>
 * @param <Class> La clase del Objeto a modificar con el {@link Controlador}
 * @param <Controlador>> La Clase del {@link #controller} esta clase
 * debe implementar la interfaz {@link com.joao.model.CRUD} con parametro del
 * mismo tipo que {@link Class}
 * @WARNING tomar en cuenta que en base al Array de String que se recibe en el constructor
 * {@link com.joao.view.CreateFrame#CreateFrame(com.joao.model.CRUD, String[])}
 * se espera que se pueda obtener todos los datos necesarios para Crear
 * un Objeto tipo {@link Class} y luego manipularle con el controlaodor
 * @see com.joao.view.CreateFrame#CreateFrame(com.joao.model.CRUD, String[])
* */
public abstract class CreateFrame<Class, Controlador extends CRUD<Class>> extends JInternalFrame implements ActionListener {
    private final Controlador controller;
    JPanel panel;
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();

    /**
     * @param controller Una instancia (de preferencia singleton) que debe implementar
     * {@link com.joao.model.CRUD}. <br/>
     * con esta se validara, y agregara objetos de tipo {@link Class} se espera que estos
     * objetos sean almacenados en el {@link com.joao.view.CreateFrame#controller}
     * @param fields Array con el nombre de todas las propiedades de un Objeto {@link Class} que
     * serán usadas para crear Labels con cada uno de los valores del array e
     * instanciar un Objeto con el input de cada label. <br/>
     * Cada valor del array es utilizado como Key de los HashMap
     * {@link #textfields}, {@link #labels}, {@link #subPanels}
     * para más información de ese proceso ver {@link #setLabelsTextFields()}
     * en alguna de las implementaciones
     * @see #setLabelsTextFields()
     * */
    public CreateFrame(Controlador controller, String[] fields) {
        this.controller = controller;
        this.fields = fields;
        customComponents();
        setVisible(true);
    }

    /**
     * establece los paneles y todo lo necesario del frame
     * */
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

            // adding subPanel to container (panel)
            panel.add(subPanels.get(field));
        }
    }

    /**
     * Revisa que todos los TextFields están llenos
     * */
    private void checkTextFields() throws Exception {
        for (Map.Entry<String, JTextField> text : textfields.entrySet()) {
            if (text.getValue().getText().isEmpty()) throw new Exception("You must fill all Fields");
        }
    }
    
    /**
     * Intenta crear un Objeto de clase {@link Class}
     * hace uso de {@link #controller} para validar todo los datos y crear el Objeto. <br/>
     * después de esta operación la Ventana se cerrara. <br/>
     * en caso de Error desplegara una Ventana {@link javax.swing.JOptionPane} con el error
     * y no se creara ningún objeto
     * @WARNING esta ventana se cerrará, después de esta operación, no solo se esconderá. <br/>
     * se hace uso de {@link #dispose()} para esta operación
     * */
    private void tryCreateObj() {
        try {
            // checks for not filled fields
            checkTextFields();

            // get Fields, validate all, and Add new Object Class to controller
            Class object = getObject();
            controller.validate(object);
            controller.create(object);

            // disable window
            this.dispose();
        } catch (Exception ex) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        tryCreateObj();
    }

}
