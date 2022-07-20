package com.joao.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    private final String[] options = {"create", "read", "update", "delete"};
    private final ArrayList<JMenuItem> userMenuItems = new ArrayList<>();

    private final ArrayList<JMenuItem> carMenuItems = new ArrayList<>();
    private final ArrayList<JMenuItem> motorcycleMenuItems = new ArrayList<>();
    private final ArrayList<JMenuItem> busMenuItems = new ArrayList<>();
    private final ArrayList<ArrayList<JMenuItem>> conveyanceItems = new ArrayList<>();

    public MainFrame() {
        customComponents();
    }


    private void customComponents() {

        // menu Bar
        JMenuBar menuBar = new JMenuBar();

        // menus
        JMenu carMenu = new JMenu("Carro");
        JMenu motorcycleMenu = new JMenu("Moto");
        JMenu busMenu = new JMenu("Camioneta");
        JMenu userMenu = new JMenu("Usuario");

        // adding arraylist of Items to conveyance Items
        conveyanceItems.add(carMenuItems);
        conveyanceItems.add(motorcycleMenuItems);
        conveyanceItems.add(busMenuItems);
        conveyanceItems.add(userMenuItems);

        // adding items to arraylist Items
        setMenuItems(options);

        //adding arraylist items to everyMenu
        JMenu[] menuArray = {carMenu, motorcycleMenu, busMenu, userMenu};
        for (int i = 0; i < menuArray.length; i++) {
            JMenu menu = menuArray[i];
            ArrayList<JMenuItem> items = conveyanceItems.get(i);
            // Agregating every item to every menu, and putting and actionlistener to every item;
            for (JMenuItem item : items) {
                menu.add(item);
                item.addActionListener(this);
            }
        }

        // button log out
        JMenu sesion = new JMenu("Sesion");
        JMenuItem menuLogOut = new JMenuItem("Cerrar sesion");
        menuLogOut.addActionListener(e -> {
            this.dispose();
            new LoginFrame();
        });

        sesion.add(menuLogOut);

        // add menus to menuBar
        menuBar.add(carMenu);
        menuBar.add(motorcycleMenu);
        menuBar.add(busMenu);
        menuBar.add(userMenu);
        menuBar.add(sesion);

        // set to main Frame
        setJMenuBar(menuBar);




        // default panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    private void setMenuItems(String[] options) {
        for (ArrayList<JMenuItem> menuItemArray : conveyanceItems) {
            for (String option : options) {
                menuItemArray.add(new JMenuItem(option));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (carMenuMethods(e.getSource())) return;
        if (motorcycleMenuMethods(e.getSource())) return;
        if (busMenuMethods(e.getSource())) return;
    }

    private boolean carMenuMethods(Object item) {
        if (item == carMenuItems.get(0)) {
            // call create frame
//            add(new CreateFrame<Car>(new String[] {"año", "capacidad", "modelo", "marca"}));
            add(new CreateFrameCar());
            return true;
        }

        if (item == carMenuItems.get(1)) {
            // call read frame
            return true;
        }

        if (item == carMenuItems.get(2)) {
            // call update frame
            return true;
        }

        if (item == carMenuItems.get(3)) {
            // call delete frame
            return true;
        }

        return false;
    }

    private boolean motorcycleMenuMethods(Object item) {
        if (item == motorcycleMenuItems.get(0)) {
            // call create frame
            return true;
        }

        if (item == motorcycleMenuItems.get(1)) {
            // call read frame
            return true;
        }

        if (item == motorcycleMenuItems.get(2)) {
            // call update frame
            return true;
        }

        if (item == motorcycleMenuItems.get(3)) {
            // call delete frame
            return true;
        }

        return false;
    }

    private boolean busMenuMethods(Object item) {
        if (item == busMenuItems.get(0)) {
            // call create frame
            return true;
        }

        if (item == busMenuItems.get(1)) {
            // call read frame
            return true;
        }

        if (item == busMenuItems.get(2)) {
            // call update frame
            return true;
        }

        if (item == busMenuItems.get(3)) {
            // call delete frame
            return true;
        }

        return false;
    }
}
/*
        // adding items to carMenu
        carMenu.add(new JMenuItem("create"));
        carMenu.add(new JMenuItem("read"));
        carMenu.add(new JMenuItem("update"));
        carMenu.add(new JMenuItem("delete"));

        // adding items to motorcycleMenu
        motorcycleMenu.add(new JMenuItem("create"));
        motorcycleMenu.add(new JMenuItem("read"));
        motorcycleMenu.add(new JMenuItem("update"));
        motorcycleMenu.add(new JMenuItem("delete"));

        // adding items to busMenu
        busMenu.add(new JMenuItem("create"));
        busMenu.add(new JMenuItem("read"));
        busMenu.add(new JMenuItem("update"));
        busMenu.add(new JMenuItem("delete"));
*/
