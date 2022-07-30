package com.joao.view;

import com.joao.model.Conveyance;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
 * JInternalFrame/Ventana que despliega una Tabla con {@link #column} como
 * la columna con los títulos y propiedades, y {@link #data} como los datos
 * expuestos en la tabla
 * */

public class ReadFrame extends JInternalFrame {
    String[][] data;
    String[] column;
    
    /**
     * @param column sera tomado como la columna con el nombre de todas las propiedades
     * para la tabla a despegar.
     * @param data serán los datos a mostrar en la tabla
     * */
    public ReadFrame(String[] column, String[][] data) {
        this.column = column;
        this.data = data;
        customComponents();
        setVisible(true);
    }

    /**
     * establece los paneles y todo lo necesario del frame
     * */
    private void customComponents() {
        // Table
        JTable table = new JTable(data, column);
        table.setBounds(30, 40, 200, 300);
        
        // ScrollPane
         JScrollPane sp=new JScrollPane(table);

        // add table to frame
        add(sp);
        
        //
        setTitle("READ");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
