package com.joao.view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class MainFrame extends JFrame implements ActionListener {

    protected JInternalFrame activeFrame;
    protected ArrayList<ArrayList<JInternalFrame>> methods;

    protected final String[] options;
    protected final ArrayList<JMenuItem> userMenuItems = new ArrayList<>();

    protected final ArrayList<JMenuItem> carMenuItems = new ArrayList<>();
    protected final ArrayList<JMenuItem> motorcycleMenuItems = new ArrayList<>();
    protected final ArrayList<JMenuItem> busMenuItems = new ArrayList<>();
    protected final ArrayList<ArrayList<JMenuItem>> menuItems = new ArrayList<>();

    /**
     * Mainframe Constructor.
     * @param options  este array especifica cuantos metodos del {@link com.joao.model.CRUD} implementara
     * @see com.joao.model.CRUD
     * */
    public MainFrame(String[] options) {
        this.options = options;
        customComponents();
        setVisible(true);
    }


    /**
     * establece los Paneles, Menus, MenuItems y todo lo necesario del frame
     * */
    private void customComponents() {
        // menu Bar
        JMenuBar menuBar = new JMenuBar();

        // menus
        JMenu carMenu = new JMenu("Carro");
        JMenu motorcycleMenu = new JMenu("Moto");
        JMenu busMenu = new JMenu("Camioneta");
        JMenu userMenu = new JMenu("Usuario");

        // adding arraylist of Items to conveyance Items
        menuItems.add(carMenuItems);
        menuItems.add(motorcycleMenuItems);
        menuItems.add(busMenuItems);
        menuItems.add(userMenuItems);

        // adding items to arraylist Items
        setMenuItems();

        //adding arraylist items to everyMenu
        JMenu[] menuArray = {carMenu, motorcycleMenu, busMenu, userMenu};
        for (int i = 0; i < menuArray.length; i++) {
            JMenu menu = menuArray[i];
            ArrayList<JMenuItem> items = menuItems.get(i);
            // Agregating every item to every menu, and putting and actionlistener to every item;
            for (JMenuItem item : items) {
                menu.add(item);
                item.addActionListener(this);
            }
        }

        // button log out
        JMenu sesion = new JMenu("Sesion");
        JMenuItem menuLogOut = new JMenuItem("Cerrar sesion");
//        JMenuItem modifyUser = new JMenuItem("Configurar Cuenta");
        menuLogOut.addActionListener(e -> {
            this.dispose();
            new LoginFrame();
        });

//        modifyUser.addActionListener(e -> {
//            add(new CuentaFrame());
//        });

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


    /**
     * por cada subArrayList dentro de {@link #menuItems} ArrayList, crea un {@link java.awt.MenuItem}
     * por cada valor de {@link #options} y lo almacena en el subArrayList
     * */
    private void setMenuItems() {
        for (ArrayList<JMenuItem> menuItemArray : menuItems) {
            for (String option : options) {
                menuItemArray.add(new JMenuItem(option));
            }
        }
    }

    /**
     * itera sobre {@link #menuItems} ArrayList y sobre sus subArrayList buscando
     * el botón que desato el evento y al encontrarlo desactiva/cierra el {@link javax.swing.JInternalFrame}
     * activo y abre uno nuevo correspondiente al botón seleccionado
     * @param item Boton o Objeto que desato el Evento
     * */
    protected void manageFrames(Object item) {
        int i = 0;
        for (ArrayList<JMenuItem> menuItem : menuItems) {
            for (int ind = 0; ind < menuItem.size(); ind++) {
                if (item == menuItem.get(ind)) {
                    // desactiveFrame
                    if (activeFrame != null) activeFrame.dispose();

                    // declare ActiveFrame
                    activeFrame = methods.get(i).get(ind);

                    // add activeFrame
                    add(activeFrame);
                    return;
                }
            }
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayFrames(e.getSource());
    }

    /**
     * Establece todos los posibles frames Que podrian ser llamados en caso de un evento ser desatado
     * y luego llama a {@link #manageFrames} para desatar el evento
     * */
    protected abstract void displayFrames(Object item);

}

