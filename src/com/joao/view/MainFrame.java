package com.joao.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class MainFrame extends JFrame implements ActionListener {

    protected final String[] options;
    protected final ArrayList<JMenuItem> userMenuItems = new ArrayList<>();

    protected final ArrayList<JMenuItem> carMenuItems = new ArrayList<>();
    protected final ArrayList<JMenuItem> motorcycleMenuItems = new ArrayList<>();
    protected final ArrayList<JMenuItem> busMenuItems = new ArrayList<>();
    protected final ArrayList<ArrayList<JMenuItem>> conveyanceItems = new ArrayList<>();

    public MainFrame(String[] options) {
        this.options = options;
        customComponents();
        setVisible(true);
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
        displayFrames(e.getSource());
    }

    protected abstract void displayFrames(Object item);

}

