package com.joao.view;

import com.joao.model.Conveyance;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.util.ArrayList;

public abstract class ReadFrame extends JInternalFrame {
    String[][] data;
    String[] column;
    public ReadFrame(String[] column) {
        this.column = column;
        customComponents();
        setVisible(true);
    }

    private void customComponents() {

        // getting arrays

        // Cars example

        JTable table = new JTable(data, column);

        // add table to frame
        add((table));
    }

    protected abstract void initialiazeTableProperties();
}
