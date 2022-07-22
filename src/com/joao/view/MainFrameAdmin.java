package com.joao.view;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import java.util.ArrayList;

public class MainFrameAdmin extends MainFrame{
    public MainFrameAdmin() {
        super(new String[] {"create", "read", "update", "delete"});
    }

    @Override
    protected void displayFrames(Object item) {
        ArrayList<ArrayList<JMenuItem>> menuItems = new ArrayList<>() {{
            add(carMenuItems);
            add(motorcycleMenuItems);
            add(busMenuItems);
            add(userMenuItems);
        }};

        ArrayList<ArrayList<JInternalFrame>> methods = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(new CreateFrameCar());
                add(new ReadFrameCar());
                //
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameMotorcycle());
                add(new ReadFrameMotorcycle());
                //
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameBus());
                add(new ReadFrameBus());
                //
                //
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameUserAdmin());
                add(new ReadFrameUser());
                //
                //
            }});
        }};

        int i = 0;
        for (ArrayList<JMenuItem> menuItem : menuItems) {
            if (item == menuItem.get(0)) {
                // Create Frame
                add(methods.get(i).get(0));
                return;
            }
            if (item == menuItem.get(1)) {
                // call read frame
                add(methods.get(i).get(1));
                return;
            }

            if (item == menuItem.get(2)) {
                // call update frame
                return;
            }

            if (item == menuItem.get(3)) {
                // call delete frame
                return;
            }
            i++;
        }
    }
}
