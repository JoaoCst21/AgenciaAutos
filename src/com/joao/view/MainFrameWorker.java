package com.joao.view;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import java.util.ArrayList;

public class MainFrameWorker extends MainFrame {
    public MainFrameWorker() {
        super(new String[] {"create", "read"});
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
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameMotorcycle());
                add(new ReadFrameMotorcycle());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameBus());
                add(new ReadFrameBus());
            }});

            add(new ArrayList<>() {{
                add(new CreateFrameUserWorker());
                add(new ReadFrameUser());
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
            i++;
        }
    }
}
