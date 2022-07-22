package com.joao.view;

import com.joao.model.Conveyance;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class ReadFrame extends JInternalFrame {
    String[][] data;
    String[] column;
    
    public ReadFrame(String[] column, String[][] data) {
        this.column = column;
        this.data = data;
        customComponents();
        setVisible(true);
    }

    private void customComponents() {
        // Table
        JTable table = new JTable(data, column);
        table.setBounds(30, 40, 200, 300);
        // JTable table = new JTable(new String[][] {{"2000", "Honda"}, {"1920", "Mercedez"}}, new String[] {"Year", "Brand"});
        
         JScrollPane sp=new JScrollPane(table);

        // add table to frame
        add(sp);
        
        //
        setTitle("READ");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
