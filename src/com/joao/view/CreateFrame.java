package com.joao.view;

import com.joao.model.Conveyance;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;

public abstract class CreateFrame extends JInternalFrame implements ActionListener {
    JPanel panel;
    String[] fields;
    HashMap<String, JPanel> subPanels = new HashMap<>();
    HashMap<String, JLabel> labels = new HashMap<>();
    HashMap<String, JTextField> textfields = new HashMap<>();

    public CreateFrame(String[] fields) {
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
}
